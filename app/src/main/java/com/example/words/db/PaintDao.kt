package com.example.words.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.words.db.model.Chairs
import com.example.words.db.model.Paint

@Dao
interface PaintDao {

    @Insert
    fun insert(paint: Paint)

    @Update
    fun updateChair(paint: Paint)

    @Query("SELECt * FROM paint")
    fun allChair():LiveData<List<Paint>>

    @Query("SELECT * FROM paint WHERE id = :id")
    suspend fun findById(id: Int): Paint

    @Query("DELETE FROM paint WHERE id = :id")
    fun delete(id: Int)

    @Update
    fun update(paint: Paint)

}