package com.example.words.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.words.db.model.Chairs
import com.example.words.db.model.Weeks

@Dao
interface ChairsDao {

    @Insert
    fun insert(chairs: Chairs)

    @Update
    fun updateChair(chairs: Chairs)

    @Query("SELECt * FROM chair")
    fun allChair():LiveData<List<Chairs>>

    @Query("SELECT * FROM chair WHERE id = :id")
    suspend fun findById(id: Int): Chairs

    @Query("DELETE FROM chair WHERE id = :id")
    fun delete(id: Int)

    @Update
    fun update(chair: Chairs)

}