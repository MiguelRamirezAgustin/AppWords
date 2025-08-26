package com.example.words.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.words.db.model.Chairs
import com.example.words.db.model.ChairsTejido

@Dao
interface ChairsTejidoDao {

    @Insert
    fun insert(chairsTejido: ChairsTejido)

    @Update
    fun updateChairTejido(chairsTejido: ChairsTejido)

    @Query("SELECt * FROM chairtejido")
    fun allChairTejido():LiveData<List<ChairsTejido>>

    @Query("SELECT * FROM chairtejido WHERE id = :id")
    suspend fun findById(id: Int): ChairsTejido

    @Query("DELETE FROM chairtejido WHERE id = :id")
    fun delete(id: Int)

    @Update
    fun update(chairtejido: ChairsTejido)

}