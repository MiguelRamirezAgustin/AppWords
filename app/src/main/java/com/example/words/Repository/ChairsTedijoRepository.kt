package com.example.words.Repository

import androidx.lifecycle.LiveData
import com.example.words.db.ChairsDao
import com.example.words.db.ChairsTejidoDao
import com.example.words.db.model.Chairs
import com.example.words.db.model.ChairsTejido
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChairsTedijoRepository @Inject constructor(private val chairsDao: ChairsTejidoDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertChairsTejido(chairs: ChairsTejido) {
        coroutineScope.launch(Dispatchers.IO) {
            chairsDao.insert(chairs)
        }
    }

    fun updateChairTejido(chairs: ChairsTejido) {
        coroutineScope.launch(Dispatchers.IO) {
            chairsDao.update(chairs)
        }
    }

    fun allChairTejido(): LiveData<List<ChairsTejido>> {
        return chairsDao.allChairTejido()
    }

    suspend fun findByIdChairTejido(id: Int): ChairsTejido {
        return chairsDao.findById(id)
    }

    fun deleteChairTejido(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            chairsDao.delete(id)
        }
    }
}