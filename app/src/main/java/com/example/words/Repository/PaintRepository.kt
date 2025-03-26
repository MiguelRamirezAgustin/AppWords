package com.example.words.Repository

import androidx.lifecycle.LiveData
import com.example.words.db.PaintDao
import com.example.words.db.model.Paint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaintRepository @Inject constructor(private val paintDao: PaintDao) {

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
        return paintDao.allPaintChair()
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