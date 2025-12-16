package com.example.words.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date



@Entity(tableName = "chairMovements")
data class ChairMovements(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val tipo: String,
    val cantidad: Int,
    val fecha: Long = System.currentTimeMillis()
)