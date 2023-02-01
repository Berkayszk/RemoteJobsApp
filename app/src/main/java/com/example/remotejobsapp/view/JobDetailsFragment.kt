package com.example.remotejobsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.remotejobsapp.MainActivity
import com.example.remotejobsapp.R
import com.example.remotejobsapp.databinding.FragmentJobDetailsBinding
import com.example.remotejobsapp.databinding.FragmentRemoteJobsBinding
import com.example.remotejobsapp.model.FavoriteJob
import com.example.remotejobsapp.model.Job
import com.example.remotejobsapp.viewmodel.RemoteJobViewModel
import com.google.android.material.snackbar.Snackbar

class JobDetailsFragment : Fragment(R.layout.fragment_job_details) {

    private var _binding : FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var currentJob : Job
    private val args : JobDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        currentJob = args.job!!

        setUpWebView()

        binding.fabAddFavorite.setOnClickListener{
            addFavJob(view)
        }

    }
    private fun addFavJob(view : View){
        val fabJob = FavoriteJob(0,
            currentJob.candidateRequiredLocation,
            currentJob.category,
            currentJob.job_type,
            currentJob.company_logo_url,
            currentJob.company_name,
            currentJob.description,
            currentJob.jobId,
            currentJob.job_type,
            currentJob.title,
            currentJob.publication_date,
            currentJob.salary,
            currentJob.url

        )
        viewModel.addFavJob(fabJob)
        Snackbar.make(view,"Job Saved Successfully",Snackbar.LENGTH_LONG).show()
    }

    private fun setUpWebView(){
        binding.webView.apply {
            webViewClient = WebViewClient()
            currentJob?.url?.let { loadUrl(it) }
        }

        val settings = binding.webView.settings
        settings.javaScriptEnabled = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setSupportZoom(false)
        settings.builtInZoomControls = false
        settings.displayZoomControls = false
        settings.textZoom = 100
        settings.blockNetworkImage = false
        settings.loadsImagesAutomatically = true
    }







    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}