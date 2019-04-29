package com.watkins.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * @author Lisa Watkins
 *
 * Checks for network connection via [ConnectivityManager]. If [ConnectivityManager.getActiveNetworkInfo] returns null,
 * we do not have a network connection established.
 */
fun hasNetwork(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

    activeNetwork?.let {
        return true
    } ?: run {
        return false
    }
}