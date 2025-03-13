package com.example.words.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.words.db.model.Weeks

@Dao
interface WeeksDao {
    @Insert
    fun insert(note: Weeks)

    @Update
    fun update(note: Weeks)

    @Query("DELETE FROM notes WHERE id = :id")
    fun delete(id: Int)

    @Query("SELECT * FROM notes")
    fun all(): LiveData<List<Weeks>>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun findById(id: Int): Weeks
}