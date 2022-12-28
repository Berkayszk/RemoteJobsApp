package com.example.remotejobsapp.api

import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET

interface RemoteJobResponse {

    @GET("remote-jobs")
    fun getRemoteJobResponse(): Call<RemoteJobResponse>
}