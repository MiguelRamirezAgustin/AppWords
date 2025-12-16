package com.example.words.Repository

import androidx.lifecycle.LiveData
import com.example.words.db.LaborDayDao
import com.example.words.db.model.LaborDay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaborDayRepository @Inject constructor(private val labor: LaborDayDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(note: LaborDay) {
        coroutineScope.launch(Dispatchers.IO) {
            labor.insert(note)
        }
    }

    fun update(note: LaborDay) {
        coroutineScope.launch(Dispatchers.IO) {
            labor.update(note)
        }
    }

    fun all(): LiveData<List<LaborDay>> {
        return labor.all()
    }

    suspend fun findById(id: Int): LaborDay {
        return labor.findById(id)
    }

    fun delete(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            labor.delete(id)
        }
    }
}