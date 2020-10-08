package com.wrightstuff.beer.viewmodel

import android.content.Context
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.wrightstuff.domain.beer.repo.BeerRepository
import com.wrightstuff.model.Beer
import com.wrightstuff.screens.beerdetail.BeerDetailViewModel
import com.wrightstuff.screens.beerlist.BeerListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class BeerDetailViewModelTest {

    @Mock
    lateinit var beerRepository: BeerRepository

    @Mock
    lateinit var context: Context

    lateinit var beerDetailViewModel: BeerDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        this.beerDetailViewModel = BeerDetailViewModel(context, beerRepository)
    }

    @Test
    fun `test grabbing a beer`() = runBlocking {
        beerDetailViewModel.grabBeer(1)
        verify(beerRepository).retrieveBeer(1)
    }

}