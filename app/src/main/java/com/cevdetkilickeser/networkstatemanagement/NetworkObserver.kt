package com.cevdetkilickeser.networkstatemanagement

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn

class NetworkObserver(
    context: Context
) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isNetworkAvailable: Boolean
        get() = connectivityManager.activeNetwork != null &&
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true

    val networkStatus: Flow<Boolean> = callbackFlow {
        val networkCallback = object : NetworkCallback() {
            override fun onUnavailable() {
                trySend(false)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }

            override fun onAvailable(network: Network) {
                trySend(true)
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        awaitClose{
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }.apply {
        distinctUntilChanged()
        flowOn(Dispatchers.IO)
    }
}