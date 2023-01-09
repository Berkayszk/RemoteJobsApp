package com.example.remotejobsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remotejobsapp.MainActivity
import com.example.remotejobsapp.R
import com.example.remotejobsapp.adapter.RemoteJobAdapter
import com.example.remotejobsapp.databinding.FragmentSearchJobBinding
import com.example.remotejobsapp.viewmodel.RemoteJobViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchJobFragment : Fragment(R.layout.fragment_search_job) {

    private var _binding : FragmentSearchJobBinding? =null
    private val binding get() = _binding!!
    private lateinit var viewModel : RemoteJobViewModel
    private lateinit var jobAdapter : RemoteJobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchJobBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchJob()
        setUpRecyclerView()
        viewModel = (activity as MainActivity).viewModel
    }

    private fun searchJob(){
        var job: Job? = null //coroutines job
        binding.etSearch.addTextChangedListener {text ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                text?.let {
                    if (text.toString().isNotEmpty()){
                        viewModel.searchRemoteJob(text.toString())
                    }
                }
            }
        }
    }
    private fun setUpRecyclerView(){
        jobAdapter = RemoteJobAdapter()
        binding.rvSearchJobs.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = jobAdapter
        }
        viewModel.searchResult().observe(viewLifecycleOwner, {remoteJob->
            jobAdapter.differ.submitList(remoteJob.jobs)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}