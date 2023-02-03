package com.example.remotejobsapp.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.remotejobsapp.MainActivity
import com.example.remotejobsapp.R
import com.example.remotejobsapp.adapter.FavJobAdapter
import com.example.remotejobsapp.adapter.RemoteJobAdapter
import com.example.remotejobsapp.databinding.FragmentSavedJobBinding
import com.example.remotejobsapp.model.FavoriteJob
import com.example.remotejobsapp.viewmodel.RemoteJobViewModel

class SavedJobFragment : Fragment(R.layout.fragment_saved_job),
    FavJobAdapter.OnItemClickListener {

    private var _binding: FragmentSavedJobBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: RemoteJobViewModel
    private lateinit var jobAdapter: FavJobAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSavedJobBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        jobAdapter = FavJobAdapter(this)

        binding.rvJobsSaved.apply {

            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(
                object : DividerItemDecoration(
                    activity, LinearLayout.VERTICAL) {})
            adapter = jobAdapter
        }

        viewModel.getAllJob().observe(viewLifecycleOwner, { FavoriteJob ->
            jobAdapter.differ.submitList(FavoriteJob)
            updateUI(FavoriteJob)
        })
    }

    private fun updateUI(list: List<FavoriteJob>) {

        if (list.isNotEmpty()) {
            binding.rvJobsSaved.visibility = View.VISIBLE
            binding.cardNoAvailable.visibility = View.GONE
        } else {
            binding.rvJobsSaved.visibility = View.GONE
            binding.cardNoAvailable.visibility = View.VISIBLE
        }
    }


    override fun onItemClick(job: FavoriteJob, view: View, position: Int) {
        deleteJob(job)
    }


    private fun deleteJob(job: FavoriteJob) {

        AlertDialog.Builder(activity).apply {
            setTitle("Delete Job")
            setMessage("Are you sure you want to permanently delete this job?")
            setPositiveButton("DELETE") { _, _ ->
                viewModel.deleteJob(job)
                Toast.makeText(activity,"Job deleted", Toast.LENGTH_SHORT).show()
            }
            setNegativeButton("CANCEL", null)
        }.create().show()

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}