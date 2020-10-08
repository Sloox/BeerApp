package com.wrightstuff.di.presentation

import com.wrightstuff.screens.MainActivity
import com.wrightstuff.di.viewmodel.ViewModelModule
import com.wrightstuff.screens.beerdetail.BeerDetailFragment
import com.wrightstuff.screens.beerlist.BeerListFragment
import dagger.Subcomponent

@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(gallery: BeerListFragment)
    fun inject(gallery: BeerDetailFragment)
}