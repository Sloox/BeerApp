package com.wrightstuff.di.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.wrightstuff.domain.beer.repo.BeerRepository
import com.wrightstuff.domain.beer.service.BeerService
import com.wrightstuff.screens.beerdetail.BeerDetailViewModel
import com.wrightstuff.screens.beerlist.BeerListViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import kotlin.reflect.KClass

@Module
class ViewModelModule {
    @MustBeDocumented
    @kotlin.annotation.Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
    @kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
    @MapKey
    internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

    @Provides
    fun viewModelFactory(providerMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelFactory {
        return ViewModelFactory(providerMap)
    }

    @Provides
    @IntoMap
    @ViewModelKey(BeerListViewModel::class)
    fun beerListViewModel(context: Context, beerRepository: BeerRepository): ViewModel {
        return BeerListViewModel(context, beerRepository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(BeerDetailViewModel::class)
    fun beerDetailViewModel(context: Context, beerRepository: BeerRepository): ViewModel {
        return BeerDetailViewModel(context, beerRepository)
    }
}