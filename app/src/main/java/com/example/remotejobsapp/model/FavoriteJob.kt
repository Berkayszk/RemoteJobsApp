package com.example.remotejobsapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "fav_job")
data class FavoriteJob(

    @PrimaryKey(autoGenerate = true)
    val id :Int,
    val candidateRequiredLocation: String?,
    val category: String?,
    val company_logo: String?,
    val company_logo_url: String?,
    val company_name: String?,
    val description: String?,
    val jobId: Int?,
    val job_type: String?,
    val publication_date: String?,
    val salary: String?,
    val title: String?,
    val url: String?
)