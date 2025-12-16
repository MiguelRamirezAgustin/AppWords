package com.example.words.Model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words.Repository.ChairsMovementsRepository
import com.example.words.Repository.ChairsTedijoRepository
import com.example.words.db.model.ChairMovements
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ChairsMovementsViewModel @Inject constructor(
    private val repository: ChairsMovementsRepository
): ViewModel() {

    val movimientos: StateFlow<List<ChairMovements>> =
        repository.getMovimientos().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val totalStock: StateFlow<Int> = movimientos.map { lista ->
        val entradas = lista.filter { it.tipo == "entrada" }.sumOf { it.cantidad }
        val salidas = lista.filter { it.tipo == "salida" }.sumOf { it.cantidad }
        entradas - salidas
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0)

    fun agregarEntrada(cantidad: Int) {
        viewModelScope.launch { repository.agregarEntrada(cantidad) }
    }

    fun registrarVenta(cantidad: Int) {
        viewModelScope.launch { repository.registrarVenta(cantidad) }
    }

    fun deleteIdMovements(id:Int){
        viewModelScope.launch { repository.deleteMovements(id) }
    }

}