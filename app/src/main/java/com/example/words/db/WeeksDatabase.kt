package com.example.words.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.words.db.model.Chairs
import com.example.words.db.model.Weeks

@Database(entities = [Weeks::class, Chairs::class] , version = 4, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeeksDatabase : RoomDatabase(){

    abstract fun notesDao(): WeeksDao
    abstract fun chairsDao(): ChairsDao

    companion object {

        @Volatile
        private var INSTANCE: WeeksDatabase? = null

        fun getInstance(context: Context): WeeksDatabase {
            // only one thread of execution at a time can enter this block of code
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WeeksDatabase::class.java,
                        "notes_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}