package com.example.words.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.words.db.model.LaborDay

@Dao
interface LaborDayDao {
    @Insert
    fun insert(laborDay: LaborDay)

    @Update
    fun update(laborDay: LaborDay)

    @Query("DELETE FROM laborDay WHERE id = :id")
    fun delete(id: Int)

    @Query("SELECT * FROM laborDay")
    fun all(): LiveData<List<LaborDay>>

    @Query("SELECT * FROM laborDay WHERE id = :id")
    suspend fun findById(id: Int): LaborDay

}