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
import com.example.remotejobsapp.adapter.FavJobAdapter
import com.example.remotejobsapp.databinding.FragmentSavedJobBinding
import com.example.remotejobsapp.model.FavoriteJob
import com.example.remotejobsapp.viewmodel.RemoteJobViewModel

class SavedJobFragment : Fragment(R.layout.fragment_saved_job) {

    private var _binding : FragmentSavedJobBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : RemoteJobViewModel
    private lateinit var favAdapter : FavJobAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedJobBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()
    }

    private fun setUpRecyclerView(){
        favAdapter = FavJobAdapter()

        binding.rvJobsSaved.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            addItemDecoration(object : DividerItemDecoration(activity,LinearLayout.VERTICAL){})
            adapter = favAdapter
        }
        viewModel.getAllFavJob().observe(viewLifecycleOwner, {favJob->
            favAdapter.differ.submitList(favJob)
            updateUI(favJob)
        })
    }

    private fun updateUI(job: List<FavoriteJob>){
         if (job.isNotEmpty()){
             binding.rvJobsSaved.visibility = View.VISIBLE
             binding.cardNoAvailable.visibility = View.GONE
         }else{
             binding.rvJobsSaved.visibility = View.GONE
             binding.cardNoAvailable.visibility = View.VISIBLE
         }

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}