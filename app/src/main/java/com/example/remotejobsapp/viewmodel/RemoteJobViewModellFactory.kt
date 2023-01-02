package com.example.remotejobsapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.remotejobsapp.api.RemoteJobApi
import com.example.remotejobsapp.repository.RemoteJobRepository

class RemoteJobViewModellFactory(
    val app: Application,
    private val remoteJobRepository: RemoteJobRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RemoteJobViewModel(app,remoteJobRepository) as T
    }
}