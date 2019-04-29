package com.watkins.lisa.util

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import javax.inject.Inject

/**
 * @author Lisa Watkins
 *
 * [LiveData] that listens for network connection via [ConnectivityManager]
 */
class ConnectivityLiveData @Inject constructor(private val connectivityManager: ConnectivityManager)
    : LiveData<Boolean>() {

    /**
     * If network connection state changes when the app is backgrounded, sometimes these callbacks are called
     * with a considerable delay. Rather than simply post "true" or "false", check network connection explicitly.
     */
    private val networkCallback = object: ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network?) {
            checkNetwork()
        }

        override fun onLost(network: Network?) {
            checkNetwork()
        }
    }

    override fun onActive() {
        super.onActive()
        checkNetwork()
        registerConnectivityCallback()
    }

    override fun onInactive() {
        super.onInactive()
        unregisterConnectivityCallback()
    }

    private fun registerConnectivityCallback() {
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    private fun unregisterConnectivityCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private fun checkNetwork() {
        with(connectivityManager.activeNetworkInfo) {
            postValue(this != null)
        }
    }
}
