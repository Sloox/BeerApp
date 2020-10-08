package com.wrightstuff.application

import android.app.Application
import com.wrightstuff.di.application.BeerAppComponent
import com.wrightstuff.di.application.BeerAppModule
import com.wrightstuff.di.application.DaggerBeerAppComponent

class BeerApplication : Application() {
    private lateinit var mBeerApplicationComponent: BeerAppComponent
    internal fun getBeerAppComponent() = mBeerApplicationComponent

    override fun onCreate() {
        super.onCreate()
        mBeerApplicationComponent = DaggerBeerAppComponent.builder()
            .beerAppModule(BeerAppModule(this))
            .build()
    }
}