package com.example.remotejobsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.remotejobsapp.databinding.JobLayoutAdapterBinding
import com.example.remotejobsapp.model.FavoriteJob
import com.example.remotejobsapp.model.Job
import com.example.remotejobsapp.view.MainFragmentDirections

class FavJobAdapter : RecyclerView.Adapter<FavJobAdapter.RemoteViewHolder>() {
    inner class RemoteViewHolder(itemBinding: JobLayoutAdapterBinding): RecyclerView.ViewHolder(itemBinding.root)

    private var binding : JobLayoutAdapterBinding? = null

    private val differCallback= object : DiffUtil.ItemCallback<FavoriteJob>()  {
        override fun areItemsTheSame(oldItem: FavoriteJob, newItem: FavoriteJob): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: FavoriteJob, newItem: FavoriteJob): Boolean {
            return oldItem==newItem
        }


    }
    val differ = AsyncListDiffer(this,differCallback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteViewHolder {
        binding = JobLayoutAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RemoteViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: RemoteViewHolder, position: Int) {
        val currentJob = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this)
                .load(currentJob.company_logo_url)
                .into(binding?.ivCompanyLogo!!)

            binding?.tvCompanyName?.text = currentJob.company_name
            binding?.tvJobLocation?.text = currentJob.candidateRequiredLocation
            binding?.tvJobTitle?.text = currentJob.title
            binding?.tvJobType?.text = currentJob.job_type
            val dateJob = currentJob.publication_date?.split("T")
            binding?.tvDate?.text = dateJob?.get(0)
        }.setOnClickListener { mView->

            val tags= arrayListOf<String>()
            val job = Job(
                currentJob.candidateRequiredLocation,
                currentJob.category,
                currentJob.company_logo_url,
                currentJob.publication_date,
                currentJob.company_name,
                currentJob.description,
                currentJob.jobId,
                currentJob.job_type,
                currentJob.title,
                currentJob.salary,
                tags,
                currentJob.company_logo,
                currentJob.url,
            )

            // --analyze--
            val direction = MainFragmentDirections.actionMainFragmentToJobDetailsFragment(job)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}