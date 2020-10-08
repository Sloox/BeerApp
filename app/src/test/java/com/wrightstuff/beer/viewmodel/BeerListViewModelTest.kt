package com.wrightstuff.beer.viewmodel

import android.content.Context
import com.nhaarman.mockitokotlin2.verify
import com.wrightstuff.domain.beer.repo.BeerRepository
import com.wrightstuff.screens.beerlist.BeerListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
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

    @Test
    fun `refresh beers test`() = runBlocking {
        beerListViewModel.refreshBeer()
        assert(beerListViewModel.beers.value == null)
        verify(beerRepository).retrieveBeerList()
    }

    //onbeerselected is a ui test not a unit test
}