package com.wrightstuff.commons.base.viewmodel

import androidx.lifecycle.ViewModel
import com.wrightstuff.commons.livedata.SingleLiveDataEvent
import com.wrightstuff.navigation.NavCommand

/**
 * BaseView model that works with dependency injection & Works with AndroidX Navigation
 * */
abstract class BaseFragmentViewModel : ViewModel() {
    val navCommand: SingleLiveDataEvent<NavCommand> = SingleLiveDataEvent()

    fun navigate(command: NavCommand) {
        navCommand.postValue(command)
    }

    open fun onAttach() {}
    open fun onDetach() {}
    open fun init() {}
}