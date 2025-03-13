package com.example.words.Repository

import androidx.lifecycle.LiveData
import com.example.words.db.WeeksDao
import com.example.words.db.model.Weeks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeeksRepository (private val notesDao: WeeksDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insert(note: Weeks) {
        coroutineScope.launch(Dispatchers.IO) {
            notesDao.insert(note)
        }
    }

    fun update(note: Weeks) {
        coroutineScope.launch(Dispatchers.IO) {
            notesDao.update(note)
        }
    }

    fun all(): LiveData<List<Weeks>> {
        return notesDao.all()
    }

    suspend fun findById(id: Int): Weeks {
        return notesDao.findById(id)
    }

    fun delete(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            notesDao.delete(id)
        }
    }
}