package com.example.words.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "materialprice")
data class MaterialPrice(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "tb_1_c18")
    var tb_1_c18: Int,
    @ColumnInfo(name = "tb_1_c20")
    var tb_1_c20: Int,
    @ColumnInfo(name = "tb_78_c18")
    var tb_78_c18: Int,
    @ColumnInfo(name = "tb_78_c20")
    var tb_78_c20: Int,
    @ColumnInfo(name = "tb_34_c18")
    var tb_34_c18: Int,
    @ColumnInfo(name = "tb_34_c20")
    var tb_34_c20: Int,
    @ColumnInfo(name = "tb_12_c18")
    var tb_12_c18 :Int,
    @ColumnInfo(name = "tb_12_c20")
    var tb_12_c20 :Int,

)