package com.hefny.hady.bellmantask.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import javax.inject.Inject

class ConnectivityUtil
@Inject
constructor(
    private val application: Application
) {
    private val TAG = "AppDebug"
    fun isConnectedToTheInternet(): Boolean {
        val cm =
            application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        try {
            return cm.activeNetworkInfo.isConnected
        } catch (e: Exception) {
            Log.e(TAG, "isConnectedToTheInternet: ${e.message}")
        }
        return false
    }
}