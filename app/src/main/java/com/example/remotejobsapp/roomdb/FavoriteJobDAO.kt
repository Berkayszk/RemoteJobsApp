package com.example.remotejobsapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.remotejobsapp.model.FavoriteJob

@Dao
interface FavoriteJobDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteJob(job : FavoriteJob)

    @Query("SELECT * FROM fav_job ORDER BY id DESC")
    fun getAllFavJob() : LiveData<List<FavoriteJob>>

    @Delete
    suspend fun deleteFavJob(job: FavoriteJob)
}