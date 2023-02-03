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

    private var _binding: FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: JobDetailsFragmentArgs by navArgs()
    private lateinit var currentJob: Job
    private lateinit var viewModel: RemoteJobViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentJobDetailsBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        currentJob = args.job!!

        setUpWebView()

        binding.fabAddFavorite.setOnClickListener {
            addJobToFavorite(view)
        }

    }

    private fun setUpWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            currentJob.url?.let { loadUrl(it) }
        }

        binding.webView.settings.apply {
            javaScriptEnabled = true
            //setAppCacheEnabled(true)
            cacheMode = WebSettings.LOAD_DEFAULT
            setSupportZoom(false)
            builtInZoomControls = false
            displayZoomControls = false
            textZoom = 100
            blockNetworkImage = false
            loadsImagesAutomatically = true
        }
    }

    private fun addJobToFavorite(view: View) {
        val job = FavoriteJob(0,currentJob.candidateRequiredLocation,currentJob.category,currentJob.company_logo
        ,currentJob.company_logo_url,currentJob.company_name,currentJob.description,currentJob.id,currentJob.job_type,
        currentJob.publication_date,currentJob.salary,currentJob.title,currentJob.url)
        viewModel.insertJob(job)
        Snackbar.make(view, "Job saved successfully", Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}