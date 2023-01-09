package com.example.remotejobsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.remotejobsapp.api.RetrofitInstance
import com.example.remotejobsapp.model.FavoriteJob
import com.example.remotejobsapp.model.RemoteJobResponse
import com.example.remotejobsapp.roomdb.FavoriteJobDatabase
import com.example.remotejobsapp.util.Constants.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteJobRepository(private val db: FavoriteJobDatabase ) {

    private val remoteJobService = RetrofitInstance.apiService
    private val remoteJobResponseLiveData: MutableLiveData<RemoteJobResponse> = MutableLiveData()
    private val searchJobResponseLiveData: MutableLiveData<RemoteJobResponse> = MutableLiveData()


    init {
        getRemoteJobResponse()
    }
    
    private fun getRemoteJobResponse(){
        remoteJobService.getRemoteJobResponse().enqueue(
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

    fun searchJobResponse(query : String?){
        remoteJobService.searchJob(query).enqueue(
            object : Callback<RemoteJobResponse>{
                override fun onResponse(
                    call: Call<RemoteJobResponse>,
                    response: Response<RemoteJobResponse>
                ) {
                    searchJobResponseLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<RemoteJobResponse>, t: Throwable) {
                    searchJobResponseLiveData.postValue(null)
                }

            }
        )
    }

    fun searchJobResult() : LiveData<RemoteJobResponse> {
        return searchJobResponseLiveData
    }

    fun remoteJobResult() : LiveData<RemoteJobResponse>{
        return remoteJobResponseLiveData
    }

    suspend fun addFavoriteJob(job : FavoriteJob) = db.getFavJobDao().addFavoriteJob(job)
    suspend fun deleteFavJob(job : FavoriteJob) = db.getFavJobDao().deleteFavJob(job)
    fun getAllFavJobs() = db.getFavJobDao().getAllFavJob()

}