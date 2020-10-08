package com.wrightstuff.beer.services

import com.nhaarman.mockitokotlin2.mock
import com.wrightstuff.domain.beer.repo.BeerRepository
import com.wrightstuff.domain.beer.repo.BeerRepositoryImpl
import com.wrightstuff.domain.beer.service.BeerService
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BeerRepositoryTest {

    lateinit var beerRepository: BeerRepository
    private val beerService: BeerService = mock {}

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        beerRepository = BeerRepositoryImpl(beerService)
    }


}