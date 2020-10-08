package com.wrightstuff.domain.beer.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wrightstuff.commons.extensions.logd
import com.wrightstuff.commons.extensions.loge
import com.wrightstuff.domain.beer.service.BeerService
import com.wrightstuff.domain.beer.status.BeerStatus
import com.wrightstuff.model.Beer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerRepositoryImpl(private val beerService: BeerService) : BeerRepository {
    private val _beers: MutableLiveData<BeerStatus> = MutableLiveData()
    override val beers: LiveData<BeerStatus> = _beers

    override suspend fun retrieveBeerList() {
        beerService.getBeers().enqueue(object : Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                logd("Retrieved beers!")
                if (response.isSuccessful) {
                    response.body()?.let {
                        _beers.postValue(BeerStatus.BeersRetrieved(it))
                    }
                } else {
                    _beers.postValue(BeerStatus.ErrorOccured("Unsuccessfully retrieved beers - ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                loge("Failed to retrieve beers - $t")
                _beers.postValue(BeerStatus.ErrorOccured("Failed to retrieve beers error - $t"))
            }
        })
    }

    override suspend fun retrieveBeer(id: Int) {
        beerService.getABeer(id).enqueue(object : Callback<List<Beer>> {
            override fun onResponse(call: Call<List<Beer>>, response: Response<List<Beer>>) {
                logd("Retrieved a beer! - id:$id")
                if (response.isSuccessful) {
                    response.body()?.let {
                        _beers.postValue(BeerStatus.SingleBeerRetrieved(it.first()))
                    }
                } else {
                    _beers.postValue(BeerStatus.ErrorOccured("Unsuccessfully retrieved the beer with id:$id - ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<Beer>>, t: Throwable) {
                loge("Failed to retrieve single beers id:$id - $t")
                _beers.postValue(BeerStatus.ErrorOccured("Failed to retrieve beer with id:$id error - $t"))
            }

        })
    }

}