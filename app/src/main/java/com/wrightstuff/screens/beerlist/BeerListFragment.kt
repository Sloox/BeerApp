package com.wrightstuff.screens.beerlist

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wrightstuff.beer.R
import com.wrightstuff.beer.databinding.FragmentBeerGalleryBinding
import com.wrightstuff.commons.base.fragment.BaseFragment
import com.wrightstuff.model.Beer
import kotlinx.android.synthetic.main.fragment_beer_gallery.*

class BeerListFragment : BaseFragment<BeerListViewModel, FragmentBeerGalleryBinding>(R.layout.fragment_beer_gallery, BeerListViewModel::class) {
    override fun onAttachInject() = presentationComponent.inject(this)

    private val adapter: BeerAdapter<Beer> by lazy {
        BeerAdapter(R.layout.adapter_item_list_beer, BeerAdapter.OnBaseAdapterClickListener { viewModel.onBeerSelected(it) })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideBackButton()
        setupRecyclerView()
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshBeer() //bug in databinding where the isRefreshing doesnt update correctly
            Handler().postDelayed(Runnable { swipeRefreshLayout?.isRefreshing = false }, 2000)
        }
    }

    private fun setupRecyclerView() {
        viewModel.beers.observe(viewLifecycleOwner, { it?.let { adapter.items = it } })
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        binding.rvBeerGrid.layoutManager = layoutManager
        binding.rvBeerGrid.adapter = adapter
    }

    @SuppressLint("RestrictedApi")
    private fun hideBackButton() {
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            show()
            setHomeButtonEnabled(false)
            setDisplayHomeAsUpEnabled(false)
            setDefaultDisplayHomeAsUpEnabled(false) //some phone models require this
        }
    }

}