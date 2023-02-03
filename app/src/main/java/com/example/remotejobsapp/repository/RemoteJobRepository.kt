package com.example.remotejobsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.remotejobsapp.api.RetrofitInstance
import com.example.remotejobsapp.model.FavoriteJob
import com.example.remotejobsapp.model.RemoteJobResponse
import com.example.remotejobsapp.roomdb.FavoriteJobDAO
import com.example.remotejobsapp.roomdb.FavoriteJobDatabase
import com.example.remotejobsapp.util.Constants.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteJobRepository(private val db: FavoriteJobDatabase ) {

    private val remoteJobService = RetrofitInstance.api
    private val remoteJobResponseLiveData: MutableLiveData<RemoteJobResponse> = MutableLiveData()
    private val searchRemoteJobLiveData: MutableLiveData<RemoteJobResponse> = MutableLiveData()


    init {

        getRemoteJobResponse()
    }

    private fun getRemoteJobResponse() {

        remoteJobService.getRemoteJobResponse().enqueue(
            object : Callback<RemoteJobResponse> {
                override fun onResponse(call: Call<RemoteJobResponse>, response: Response<RemoteJobResponse>) {
                    if (response.body() != null) {
                        remoteJobResponseLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<RemoteJobResponse>, t: Throwable) {
                    remoteJobResponseLiveData.postValue(null)
                    Log.d("error ibm", t.message.toString())
                }
            })
    }


    fun searchRemoteJob(query: String?) {
        remoteJobService.searchJob(query).enqueue(
            object : Callback<RemoteJobResponse> {
                override fun onResponse(call: Call<RemoteJobResponse>, response: Response<RemoteJobResponse>) {
                    if (response.body() != null) {
                        searchRemoteJobLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<RemoteJobResponse>, t: Throwable) {
                    searchRemoteJobLiveData.postValue(null)
                    Log.d("error ibm", t.message.toString())
                }

            }
        )

    }


    fun getRemoteJobResponseLiveData(): LiveData<RemoteJobResponse> {
        return remoteJobResponseLiveData
    }

    fun getSearchJobResponseLiveData(): LiveData<RemoteJobResponse> {
        return searchRemoteJobLiveData
    }


    suspend fun insertJob(job: FavoriteJob) = db.getRemoteJobDao().addFavoriteJob(job)
    suspend fun deleteJob(job: FavoriteJob) = db.getRemoteJobDao().deleteFavJob(job)
    fun getAllJobs() = db.getRemoteJobDao().getAllFavJob()

}
