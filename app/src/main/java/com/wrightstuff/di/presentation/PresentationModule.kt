package com.wrightstuff.di.presentation

import android.app.Activity
import android.content.Context
import com.wrightstuff.domain.beer.repo.BeerRepository
import com.wrightstuff.domain.beer.repo.BeerRepositoryImpl
import com.wrightstuff.domain.beer.service.BeerService
import com.wrightstuff.domain.beer.service.BeerServiceGenerator
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val mActivity: Activity) {
    @Provides
    fun getActivity(): Activity = mActivity

    @Provides
    fun context(activity: Activity): Context = activity

    @Provides
    fun getBeerService(): BeerService = BeerServiceGenerator.createService(BeerService::class.java)

    @Provides
    fun getBeerRepo(beerService: BeerService): BeerRepository = BeerRepositoryImpl(beerService)
}