package com.wrightstuff.commons.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders
import com.wrightstuff.commons.base.viewmodel.BaseActivityViewModel
import com.wrightstuff.di.viewmodel.ViewModelFactory
import javax.inject.Inject
import kotlin.reflect.KClass
import com.wrightstuff.beer.BR


abstract class BaseMvvmActivity<vm : BaseActivityViewModel, B : ViewDataBinding>(@LayoutRes val layout: Int, private val vmClass: KClass<vm>) :
    AppCompatActivity() {
    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    lateinit var viewModel: vm
    
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this, mViewModelFactory).get(vmClass.javaObjectType)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, layout, null, false)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        binding.setViewModel(viewModel)
    }
}

fun ViewDataBinding.setViewModel(vm: BaseActivityViewModel) = apply {
    setVariable(BR.viewModel, vm)
    executePendingBindings()
}