package com.wrightstuff.commons.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.annotation.UiThread
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.wrightstuff.di.presentation.PresentationModule
import com.wrightstuff.application.BeerApplication
import com.wrightstuff.commons.base.viewmodel.BaseFragmentViewModel
import com.wrightstuff.di.application.BeerAppComponent
import com.wrightstuff.di.presentation.PresentationComponent
import com.wrightstuff.di.viewmodel.ViewModelFactory
import com.wrightstuff.navigation.NavCommand
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<vm : BaseFragmentViewModel, B : ViewDataBinding>(
    @LayoutRes layout: Int,
    private val vmClass: KClass<vm>
) : Fragment(layout) {
    private val navObserver: Observer<NavCommand> = Observer { onNavigationCommand(it) }

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    lateinit var viewModel: vm

    open val binding: B by bind() //bind the viewmodel to the fragment by settings its viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
    }

    abstract fun onAttachInject()

    override fun onAttach(context: Context) {
        onAttachInject()
        viewModel = ViewModelProviders.of(this, mViewModelFactory).get(vmClass.javaObjectType)
        super.onAttach(context)
        viewModel.navCommand.observe(this, navObserver)
        viewModel.onAttach()
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.navCommand.removeObserver(navObserver)
        viewModel.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this //update the ui when changes occur & lazy instantiate the binding value
    }

    open fun onNavigationCommand(command: NavCommand) {
        when (command) {
            is NavCommand.Back -> findNavController().navigateUp()
            is NavCommand.BeerDetailsFragment -> findNavController().navigate(command.cmdID, bundleOf("beerID" to command.beerID))
            else -> findNavController().navigate(command.cmdID)
        }
    }

    private var mIsInjectorUsed = false

    @get:UiThread
    protected val presentationComponent: PresentationComponent
        get() {
            if (mIsInjectorUsed) {
                throw RuntimeException("there is no need to use injector more than once")
            }
            mIsInjectorUsed = true
            return applicationComponent.newPresentationComponent(PresentationModule(requireActivity()))
        }

    private val applicationComponent: BeerAppComponent
        get() = (requireActivity().application as BeerApplication).getBeerAppComponent()
}