package com.wrightstuff.di.application

import com.wrightstuff.di.presentation.PresentationModule
import com.wrightstuff.di.presentation.PresentationComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BeerAppModule::class])
interface BeerAppComponent {
    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent
}