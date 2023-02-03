package com.example.remotejobsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.remotejobsapp.model.FavoriteJob
import com.example.remotejobsapp.repository.RemoteJobRepository
import kotlinx.coroutines.launch

class RemoteJobViewModel(
    app: Application,
    private val remoteJobRepository: RemoteJobRepository
) : AndroidViewModel(app) {

    fun remoteJobResult() =
        remoteJobRepository.getRemoteJobResponseLiveData()

    fun searchJob(query: String?) =
        remoteJobRepository.searchRemoteJob(query)

    fun searchResult() = remoteJobRepository.getSearchJobResponseLiveData()

    fun insertJob(job: FavoriteJob) = viewModelScope.launch {
        remoteJobRepository.insertJob(job)
    }

    fun deleteJob(job: FavoriteJob) = viewModelScope.launch {
        remoteJobRepository.deleteJob(job)
    }

    fun getAllJob() = remoteJobRepository.getAllJobs()

}