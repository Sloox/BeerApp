package com.wrightstuff.domain.beer.service

import com.wrightstuff.model.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BeerService {
    @GET("beers/{id}")
    fun getABeer(@Path("id") id: Int): Call<List<Beer>>

    @GET("beers")
    fun getBeers(): Call<List<Beer>>
}