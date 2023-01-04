package com.example.remotejobsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.remotejobsapp.databinding.ActivityMainBinding
import com.example.remotejobsapp.repository.RemoteJobRepository
import com.example.remotejobsapp.roomdb.FavoriteJobDatabase
import com.example.remotejobsapp.viewmodel.RemoteJobViewModel
import com.example.remotejobsapp.viewmodel.RemoteJobViewModellFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel : RemoteJobViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        setUpViewModel()

    }
    private fun setUpViewModel(){
        val remoteJobRepository = RemoteJobRepository(
            FavoriteJobDatabase(this)
        )
        val viewModelProviderFactory = RemoteJobViewModellFactory(application,remoteJobRepository)

        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(RemoteJobViewModel::class.java)
    }
}