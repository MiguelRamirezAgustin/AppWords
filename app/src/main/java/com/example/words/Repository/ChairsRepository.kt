package com.example.words.Repository

import androidx.lifecycle.LiveData
import com.example.words.db.ChairsDao
import com.example.words.db.model.Chairs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChairsRepository @Inject constructor(private val chairsDao: ChairsDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertChairs(chairs: Chairs) {
        coroutineScope.launch(Dispatchers.IO) {
            chairsDao.insert(chairs)
        }
    }

    fun updateChair(chairs: Chairs) {
        coroutineScope.launch(Dispatchers.IO) {
            chairsDao.update(chairs)
        }
    }

    fun allChair(): LiveData<List<Chairs>> {
        return chairsDao.allChair()
    }

    suspend fun findByIdChair(id: Int): Chairs {
        return chairsDao.findById(id)
    }

    fun deleteChair(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            chairsDao.delete(id)
        }
    }
}