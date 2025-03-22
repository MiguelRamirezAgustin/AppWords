package com.example.words.Repository

import androidx.lifecycle.LiveData
import com.example.words.db.ChairsDao
import com.example.words.db.PaintDao
import com.example.words.db.WeeksDao
import com.example.words.db.model.Chairs
import com.example.words.db.model.Paint
import com.example.words.db.model.Weeks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PaintRepository (private val paintDao: PaintDao) {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun insertPaint(paint: Paint) {
        coroutineScope.launch(Dispatchers.IO) {
            paintDao.insert(paint)
        }
    }

    fun updatePaint(paint: Paint) {
        coroutineScope.launch(Dispatchers.IO) {
            paintDao.update(paint)
        }
    }

    fun allPaint(): LiveData<List<Paint>> {
        return paintDao.allChair()
    }

    suspend fun findByIdPaint(id: Int): Paint {
        return paintDao.findById(id)
    }

    fun deleteChair(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            paintDao.delete(id)
        }
    }
}