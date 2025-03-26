package com.example.words.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.words.db.model.Chairs
import com.example.words.db.model.Paint
import com.example.words.db.model.LaborDay

@Database(entities = [Chairs::class, Paint::class, LaborDay::class] , version = 9, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun chairsDao(): ChairsDao
    abstract fun paintDao():PaintDao
    abstract fun laborDayDao(): LaborDayDao


}