package com.example.words.Model

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words.Repository.ChairsRepository
import com.example.words.Repository.PaintRepository
import com.example.words.core.TextFieldState
import com.example.words.db.model.Chairs
import com.example.words.db.model.Paint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class PaintViewModel @Inject constructor(
    private val repository: PaintRepository)
    : ViewModel() {


    val all: LiveData<List<Paint>> = repository.allPaint()


    fun insertPaint(
        sillaGrande: Int,
        sillaChica: Int,
        sillaindividual: Int,
        papelera: Int,
        listonero: Int,
        botanero: Int,
        total: String,
        arana: Int,
        nota: String,
    ) {
        viewModelScope.launch {
            val newPaint = Paint(
                null,
                listonero = listonero.toString(),
                botanero = botanero.toString(),
                papelera = papelera.toString(),
                arana = arana.toString(),
                sillaGrande = sillaGrande.toString(),
                sillaIndividual = sillaindividual.toString(),
                sillaChica = sillaChica.toString(),
                total = total,
                nota = nota,
                update = Date()
            )
            repository.insertPaint(newPaint)
        }
    }

    fun deletePaint(chair: Int?) {
        viewModelScope.launch {
            chair?.let { repository.deleteChair(it) }
        }
    }


}