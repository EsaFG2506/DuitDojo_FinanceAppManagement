package com.capstone.duitdojo_financeappmanagement.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.capstone.duitdojo_financeappmanagement.data.model.MenuItem

@Dao
interface PhotoDao {

    @Query("SELECT * FROM nota")
    fun getAllNota(): LiveData<List<MenuItem>>
}