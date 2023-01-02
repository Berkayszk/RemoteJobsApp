package com.example.remotejobsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.remotejobsapp.api.RemoteJobApi
import com.example.remotejobsapp.api.RetrofitInstance
import com.example.remotejobsapp.model.RemoteJobResponse
import com.example.remotejobsapp.util.Constants.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteJobRepository {

    private val remotoJobService = RetrofitInstance.apiService
    private val remoteJobResponseLiveData: MutableLiveData<RemoteJobResponse> = MutableLiveData()

    init {
        getRemoteJobResponse()
    }
    
    private fun getRemoteJobResponse(){
        remotoJobService.getRemoteJobResponse().enqueue(
            object : Callback<RemoteJobResponse> {
                override fun onResponse(
                    call: Call<RemoteJobResponse>,
                    response: Response<RemoteJobResponse>
                ) {
                    remoteJobResponseLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<RemoteJobResponse>, t: Throwable) {
                    remoteJobResponseLiveData.postValue(null)
                    Log.e(TAG,"onFailure: ${t.message}")
                }

            }
        )
    }

    fun remoteJobResult() : LiveData<RemoteJobResponse>{
        return remoteJobResponseLiveData
    }

}