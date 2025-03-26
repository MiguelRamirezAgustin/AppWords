package com.example.words.db

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun fromDate(value: Date?): Long? {
        return value?.time
    }

    // Convierte un Long (timestamp) a Date
    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }
}