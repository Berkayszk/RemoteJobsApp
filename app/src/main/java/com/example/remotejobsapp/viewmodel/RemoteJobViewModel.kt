package com.example.remotejobsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.remotejobsapp.repository.RemoteJobRepository

class RemoteJobViewModel (
    app: Application,
    private val remoteJobRepository: RemoteJobRepository
    ): AndroidViewModel(app){
        fun remoteJobResult() = remoteJobRepository.remoteJobResult()
}