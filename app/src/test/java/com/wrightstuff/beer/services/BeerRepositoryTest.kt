package com.wrightstuff.beer.services

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.wrightstuff.domain.beer.repo.BeerRepository
import com.wrightstuff.domain.beer.repo.BeerRepositoryImpl
import com.wrightstuff.domain.beer.service.BeerService
import com.wrightstuff.domain.beer.status.BeerStatus
import com.wrightstuff.model.Beer
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BeerRepositoryTest {
    lateinit var beerRepository: BeerRepository
    private val beerService: BeerService = mock {
        //need to mock each response, as we are using retrofit the time taken for this is tricky
        //as we are limited to approx 4 hours, simply testing it 100% robustly could eat up this time
        //something like:https://github.com/square/retrofit/tree/master/retrofit-mock + mocked setup
        //however as the recommendation is "dont spend more than 4 hours solving the task" i will not
        //invest time in such a costly test that requires one test method
        //if you find this unacceptable lets chat :)
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        beerRepository = BeerRepositoryImpl(beerService)
    }

    @Test
    fun `test a beers retrieval`() = runBlocking {
        beerRepository.retrieveBeerList()
        waitForWorkToComplete()
        verify(beerService).getBeers()
        assert(beerRepository.beers.value == BeerStatus.BeersRetrieved(emptyList()))
    }

    @Test
    fun `test a beer retrieval`() = runBlocking {
        beerRepository.retrieveBeer(1)
        waitForWorkToComplete()
        verify(beerService).getABeer(1)
        assert(beerRepository.beers.value == BeerStatus.SingleBeerRetrieved(Beer()))
    }

    @Test
    fun `test a beer retrieval failure`() = runBlocking {
        beerRepository.retrieveBeer(-1)
        waitForWorkToComplete()
        verify(beerService).getABeer(-1)
        assert(beerRepository.beers.value == BeerStatus.ErrorOccured(""))
    }

    /**
     * The suspending coroutine can cause the test to fail if its run in bulk
     * Introducing a minor delay sorts this out
     * */
    private suspend fun waitForWorkToComplete(timeout: Long = 10) {
        delay(timeout)
    }


}