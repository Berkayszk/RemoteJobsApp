package com.example.remotejobsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remotejobsapp.MainActivity
import com.example.remotejobsapp.R
import com.example.remotejobsapp.adapter.RemoteJobAdapter
import com.example.remotejobsapp.databinding.FragmentRemoteJobsBinding
import com.example.remotejobsapp.util.Constants
import com.example.remotejobsapp.viewmodel.RemoteJobViewModel

class RemoteJobsFragment : Fragment(R.layout.fragment_remote_jobs) {

    private var _binding: FragmentRemoteJobsBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel : RemoteJobViewModel
    private lateinit var  remoteJobAdapter: RemoteJobAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRemoteJobsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel

        setUpRecyclerView()

    }
    private fun setUpRecyclerView(){
        remoteJobAdapter = RemoteJobAdapter()

        binding.rvRemoteJobs.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(object : DividerItemDecoration(activity,LinearLayout.VERTICAL) {})

            adapter = remoteJobAdapter
        }
        fetchinData()
    }


    private fun fetchinData(){

        if (Constants.checkInternetConnection(requireContext())){
            viewModel.remoteJobResult().observe(viewLifecycleOwner, {remoteJob->
                if (remoteJob !=null){
                    remoteJobAdapter.differ.submitList(remoteJob.jobs)
                }
            })
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}