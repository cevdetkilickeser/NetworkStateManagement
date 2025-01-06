package com.cevdetkilickeser.networkstatemanagement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class MainViewModel(
    networkObserver: NetworkObserver
) : ViewModel() {

    val networkStatus = networkObserver.networkStatus.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = networkObserver.isNetworkAvailable
    )
}