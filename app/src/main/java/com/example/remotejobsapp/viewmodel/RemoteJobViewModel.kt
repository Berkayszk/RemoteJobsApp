package com.example.remotejobsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.remotejobsapp.model.FavoriteJob
import com.example.remotejobsapp.repository.RemoteJobRepository
import kotlinx.coroutines.launch

class RemoteJobViewModel (
    app: Application,
    private val remoteJobRepository: RemoteJobRepository
    ): AndroidViewModel(app){
        fun remoteJobResult() = remoteJobRepository.remoteJobResult()

    fun addFavJob(job : FavoriteJob) = viewModelScope.launch {
        remoteJobRepository.addFavoriteJob(job)
    }
    fun deleteJob(job : FavoriteJob) = viewModelScope.launch {
        remoteJobRepository.deleteFavJob(job)
    }
    fun getAllFavJob() = remoteJobRepository.getAllFavJobs()

    fun searchRemoteJob(query : String?) = remoteJobRepository.searchJobResponse(query)

    fun searchResult() = remoteJobRepository.searchJobResult()


}