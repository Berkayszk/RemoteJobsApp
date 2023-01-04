package com.example.remotejobsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.remotejobsapp.R
import com.example.remotejobsapp.databinding.FragmentJobDetailsBinding
import com.example.remotejobsapp.databinding.FragmentRemoteJobsBinding
import com.example.remotejobsapp.model.FavoriteJob
import com.example.remotejobsapp.model.Job

class JobDetailsFragment : Fragment(R.layout.fragment_job_details) {

    private var _binding : FragmentJobDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var currentJob : Job
    private val args : JobDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJobDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentJob = args.job!!

        setUpWebView()

        binding.fabAddFavorite.setOnClickListener{
            addFavJob(view)
        }

    }
    private fun addFavJob(view : View){
        val fabJob = FavoriteJob(
            currentJob.id!!,currentJob.candidateRequiredLocation,
            currentJob.category,currentJob.job_type,currentJob.company_logo_url,
            currentJob.company_name,currentJob.description,currentJob.jobId,
        )
    }

    private fun setUpWebView(){
        binding.webView.apply {
            webViewClient = WebViewClient()
            currentJob?.url?.let { loadUrl(it) }
        }
    }







    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}