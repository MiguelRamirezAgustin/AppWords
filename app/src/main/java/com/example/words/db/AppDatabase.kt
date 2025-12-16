package com.example.words.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.words.db.model.ChairMovements
import com.example.words.db.model.Chairs
import com.example.words.db.model.ChairsTejido
import com.example.words.db.model.Paint
import com.example.words.db.model.LaborDay
import com.example.words.db.model.MaterialPrice

@Database(entities = [Chairs::class, Paint::class, LaborDay::class, ChairsTejido::class, ChairMovements::class, MaterialPrice::class] , version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun chairsDao(): ChairsDao
    abstract fun paintDao():PaintDao
    abstract fun laborDayDao(): LaborDayDao
    abstract fun chairTejido(): ChairsTejidoDao
    abstract fun chairsMovements(): ChairMovementsDAO
    abstract fun materialPrice(): MaterialPriceDAO


}