package com.example.words.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "chairtejido")
data class ChairsTejido(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "mecedora_grande")
    var sillaGrande: String,
    @ColumnInfo(name = "mecedora_chica")
    var sillaChica: String,
    @ColumnInfo(name = "silla_individual")
    var sillaIndividual: String,
    @ColumnInfo(name = "bancos")
    var bancos: String,
    @ColumnInfo(name = "cuadrados")
    var cuadrados: String,
    @ColumnInfo(name = "cuadrado_mini")
    var cuadrado_mini: String,
    @ColumnInfo(name = "total")
    var total :String,
    @ColumnInfo(name = "nota")
    var nota :String,
    @ColumnInfo(name = "update")
    var update: Date?
)