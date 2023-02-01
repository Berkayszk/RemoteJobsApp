package com.example.remotejobsapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

object Constants {
    const val BASE_URL="https://remotive.com/api/"
    const val TAG ="error api"

    fun checkInternetConnection(context : Context) : Boolean{
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network)?: return false

        return when{
            activeNetwork.hasCapability(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasCapability(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasCapability(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

    }
}