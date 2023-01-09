package com.example.remotejobsapp.api

import com.example.remotejobsapp.model.RemoteJobResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteJobApi {

    @GET("remote-jobs?limit=5") //normal remote-jobs, remote-jobs?limit=5 for picture
    fun getRemoteJobResponse(): Call<RemoteJobResponse>

    @GET("remote-jobs")
    fun searchJob(@Query("search") query: String?) : Call<RemoteJobResponse>


}