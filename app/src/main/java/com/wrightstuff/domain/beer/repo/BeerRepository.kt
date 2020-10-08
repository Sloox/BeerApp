package com.wrightstuff.domain.beer.repo

import androidx.lifecycle.LiveData
import com.wrightstuff.domain.beer.status.BeerStatus

interface BeerRepository {
    val beers: LiveData<BeerStatus>

    suspend fun retrieveBeerList()
    suspend fun retrieveBeer(id: Int)

}