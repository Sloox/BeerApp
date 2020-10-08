package com.wrightstuff.screens.beerdetail

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wrightstuff.commons.base.viewmodel.BaseFragmentViewModel
import com.wrightstuff.commons.extensions.loge
import com.wrightstuff.domain.beer.repo.BeerRepository
import com.wrightstuff.model.Beer
import com.wrightstuff.domain.beer.status.BeerStatus
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BeerDetailViewModel @Inject constructor(private val context: Context, private val beerRepository: BeerRepository) : BaseFragmentViewModel() {
    private val _singleBeer: MutableLiveData<Beer> = MutableLiveData()
    val singleBeer: LiveData<Beer> = _singleBeer

    var error_text: String? = null

    /*Observers*/
    private val beerObserver = Observer<BeerStatus> {
        when (it) {
            is BeerStatus.SingleBeerRetrieved -> _singleBeer.postValue(it.beer)
            is BeerStatus.ErrorOccured -> {
                error_text = it.error
                Toast.makeText(context, "Failed to retrieve beer!\n$error_text", Toast.LENGTH_LONG).show()
                loge("Error occured: ${it.error}")
            }
            else -> loge("Unknown result has occured : $it")
        }
    }

    override fun onAttach() {
        super.onAttach()
        beerRepository.beers.observeForever(beerObserver)
    }

    override fun onDetach() {
        super.onDetach()
        beerRepository.beers.removeObserver(beerObserver)
    }

    //scoped coroutine
    fun grabBeer(singleBeerID: Int) = runBlocking {
        launch {
            beerRepository.retrieveBeer(singleBeerID) //delegate getting some beers to the service layer
        }
    }
}