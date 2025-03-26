package com.example.words.Repository

import androidx.lifecycle.LiveData
import com.example.words.db.LaborDayDao
import com.example.words.db.model.LaborDay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LaborDayRepository @Inject constructor(private val notesDao: LaborDayDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(note: LaborDay) {
        coroutineScope.launch(Dispatchers.IO) {
            notesDao.insert(note)
        }
    }

    fun update(note: LaborDay) {
        coroutineScope.launch(Dispatchers.IO) {
            notesDao.update(note)
        }
    }

    fun all(): LiveData<List<LaborDay>> {
        return notesDao.all()
    }

    suspend fun findById(id: Int): LaborDay {
        return notesDao.findById(id)
    }

    fun delete(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            notesDao.delete(id)
        }
    }
}