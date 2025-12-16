package com.example.words.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.words.db.model.MaterialPrice


@Dao
interface MaterialPriceDAO {

    @Insert
    suspend fun insert(materialprice: MaterialPrice)

    @Update
    fun update(materialprice: MaterialPrice)

    @Query("SELECT * FROM materialprice")
    fun all(): LiveData<List<MaterialPrice>>

    @Query("DELETE FROM materialprice WHERE id = :id")
    fun delete(id: Int)
}