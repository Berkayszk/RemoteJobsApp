package com.example.remotejobsapp.model

import com.google.gson.annotations.SerializedName

data class RemoteJobResponse(
    @SerializedName("job-count")
    val jobCount: Int?,
    @SerializedName("0-legal-notice")
    val legalNotice: String?,
    val jobs: List<Job?>?
)