package com.example.words.Repository

import com.example.words.db.ChairMovementsDAO
import com.example.words.db.ChairsTejidoDao
import com.example.words.db.model.ChairMovements
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class ChairsMovementsRepository @Inject constructor(private val chairsMovementsDao: ChairMovementsDAO) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    fun getMovimientos() = chairsMovementsDao.getMovimientos()

    suspend fun agregarEntrada(cantidad: Int) {
        chairsMovementsDao.insert(ChairMovements(tipo = "entrada", cantidad = cantidad))
    }

    suspend fun registrarVenta(cantidad: Int) {
        chairsMovementsDao.insert(ChairMovements(tipo = "salida", cantidad = cantidad))
    }

    suspend fun deleteMovements(id:Int){
        coroutineScope.launch(Dispatchers.IO) {
            chairsMovementsDao.delete(id)
        }
    }
}