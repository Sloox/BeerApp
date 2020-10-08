package com.wrightstuff.domain.beer.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Simple Service generator for retrofit config options
 * */
object BeerServiceGenerator {
    private val BASE_URL: String = "https://api.punkapi.com/v2/"
    private val builder = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
    private val httpClient = OkHttpClient.Builder()
    private val retrofit = builder.client(httpClient.build()).build()

    fun <S> createService(serviceClass: Class<S>?): S {
        return retrofit.create(serviceClass)
    }

}