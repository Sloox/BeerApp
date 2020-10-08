package com.wrightstuff.screens.beerlist

import android.content.Context
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wrightstuff.commons.base.viewmodel.BaseFragmentViewModel
import com.wrightstuff.commons.extensions.loge
import com.wrightstuff.domain.beer.repo.BeerRepository
import com.wrightstuff.model.Beer
import com.wrightstuff.domain.beer.service.BeerService
import com.wrightstuff.domain.beer.status.BeerStatus
import com.wrightstuff.navigation.NavCommand
import kotlinx.android.synthetic.main.fragment_beer_gallery.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BeerListViewModel @Inject constructor(private val context: Context, private val beerRepository: BeerRepository) : BaseFragmentViewModel() {
    private val _beers: MutableLiveData<List<Beer>> = MutableLiveData()
    val beers: LiveData<List<Beer>> = _beers

    /*Observers*/
    private val beerObserver = Observer<BeerStatus> {
        when (it) {
            is BeerStatus.BeersRetrieved -> _beers.postValue(it.beers)
            is BeerStatus.ErrorOccured -> {
                Toast.makeText(context, "Error getting beers!\n${it.error}", Toast.LENGTH_LONG).show()
                loge("Error occured: ${it.error}")
            }
        }
    }

    override fun onAttach() {
        super.onAttach()
        beerRepository.beers.observeForever(beerObserver)
        grabBeers()
    }

    override fun onDetach() {
        super.onDetach()
        beerRepository.beers.removeObserver(beerObserver)
    }

    //scoped coroutine
    private fun grabBeers() = runBlocking {
        launch {
            beerRepository.retrieveBeerList() //delegate getting some beers to the service layer
        }
    }

    fun refreshBeer() {
        _beers.postValue(null)
        grabBeers()
    }

    fun onBeerSelected(clickedBeer: Beer) {
        navigate(NavCommand.BeerDetailsFragment(clickedBeer.id ?: 0))
    }
}