package com.example.remotejobsapp.api

import com.example.remotejobsapp.model.RemoteJobResponse
import retrofit2.Call
import retrofit2.http.GET

interface RemoteJobApi {

    @GET("remote-jobs") //remote-jobs?limit=5 for picture
    fun getRemoteJobResponse(): Call<RemoteJobResponse>
}