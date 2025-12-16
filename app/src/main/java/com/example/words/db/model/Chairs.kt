package com.example.words.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "chair")
data class Chairs(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "mecedora_grande")
    var sillaGrande: String,
    @ColumnInfo(name = "mecedora_chica")
    var sillaChica: String,
    @ColumnInfo(name = "silla_individual")
    var sillaIndividual: String,
    @ColumnInfo(name = "papelera")
    var papelera: String,
    @ColumnInfo(name = "listonero")
    var listonero: String,
    @ColumnInfo(name = "botanero")
    var botanero: String,
    @ColumnInfo(name = "sueldo")
    var sueldo :String,
    @ColumnInfo(name = "bancos")
    var bancos :String,
    @ColumnInfo(name = "porta_garrafon")
    var portaGarrafon :String,
    @ColumnInfo(name = "listonero_cortados")
    var listoneroCortados :String,
    @ColumnInfo(name = "nota")
    var nota :String,
    @ColumnInfo(name = "update")
    var update: Date?
)