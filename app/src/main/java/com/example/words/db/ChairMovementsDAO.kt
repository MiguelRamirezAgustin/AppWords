package com.example.words.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.words.db.model.ChairMovements
import kotlinx.coroutines.flow.Flow


@Dao
interface ChairMovementsDAO {
    @Insert
    suspend fun insert(movimiento: ChairMovements)

    @Query("SELECT * FROM ChairMovements ORDER BY fecha DESC")
    fun getMovimientos(): Flow<List<ChairMovements>>


    @Query("DELETE FROM ChairMovements WHERE id = :id")
    fun delete(id: Int)
}