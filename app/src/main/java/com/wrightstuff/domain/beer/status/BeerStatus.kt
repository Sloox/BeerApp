package com.wrightstuff.domain.beer.status

import com.wrightstuff.model.Beer

/**
 * Simplifiy the retrieval status in a sealed class
 * For larger projects need a better pattern
 * */
sealed class BeerStatus(val message: String = "") {
    data class BeersRetrieved(val beers: List<Beer>) : BeerStatus("ok")
    data class SingleBeerRetrieved(val beer: Beer) : BeerStatus("ok")
    data class ErrorOccured(val error: String) : BeerStatus(error)
}