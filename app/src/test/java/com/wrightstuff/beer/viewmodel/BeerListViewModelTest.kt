package com.wrightstuff.beer.viewmodel

import android.content.Context
import com.wrightstuff.domain.beer.repo.BeerRepository
import com.wrightstuff.screens.beerlist.BeerListViewModel
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class BeerListViewModelTest {

    @Mock
    lateinit var beerRepository: BeerRepository

    @Mock
    lateinit var context: Context

    lateinit var beerListViewModel: BeerListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        this.beerListViewModel = BeerListViewModel(context, beerRepository)
    }

}