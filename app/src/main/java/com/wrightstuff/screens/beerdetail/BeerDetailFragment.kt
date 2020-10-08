package com.wrightstuff.screens.beerdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wrightstuff.beer.R
import com.wrightstuff.beer.databinding.FragmentBeerDetailsBinding
import com.wrightstuff.commons.base.fragment.BaseFragment
import com.wrightstuff.navigation.NavCommand
import com.wrightstuff.screens.beerdetail.adapters.HopsAdapter
import com.wrightstuff.screens.beerdetail.adapters.MaltAdapter
import com.wrightstuff.screens.beerdetail.adapters.MethodAdapter
import kotlinx.android.synthetic.main.fragment_beer_details.*

class BeerDetailFragment : BaseFragment<BeerDetailViewModel, FragmentBeerDetailsBinding>(R.layout.fragment_beer_details, BeerDetailViewModel::class) {
    override fun onAttachInject() = presentationComponent.inject(this)
    private var beerID: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        beerID = arguments?.getInt("beerID") ?: 0
        setupBeerDetails(beerID)
    }

    private fun setupBeerDetails(beerID: Int) {
        viewModel.singleBeer.observe(viewLifecycleOwner, {
            Glide.with(requireContext())
                .load(it.imageUrl)
                .into(beerView)
        })
        viewModel.grabBeer(beerID)
    }

    override fun onResume() {
        super.onResume()
        showToolbar()
    }

    @SuppressLint("RestrictedApi")
    private fun showToolbar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            show()
            setHomeButtonEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDefaultDisplayHomeAsUpEnabled(true) //some phone models require this
        }
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                viewModel.navigate(NavCommand.BeerSelectionFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}