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
import com.example.words.core.TextFieldState
import com.example.words.db.model.Chairs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ChairsViewModel @Inject constructor(
    private val repository: ChairsRepository
) : ViewModel() {



    val all: LiveData<List<Chairs>> = repository.allChair()

    fun insertChair(
        sillaGrnade: Int,
        sillaChica: Int,
        sillaindividual: Int,
        papelera: Int,
        listonero: Int,
        botanero: Int,
        sueldo: String,
    ) {
        viewModelScope.launch {
            val newChair = Chairs(
                null,
                sillaGrnade.toString(),
                sillaChica.toString(),
                sillaindividual.toString(),
                papelera.toString(),
                listonero.toString(),
                botanero.toString(),
                sueldo = sueldo,
                Date()
            )
            repository.insertChairs(newChair)
        }
    }

    fun deleteChair(chair: Int?) {
        viewModelScope.launch {
            chair?.let { repository.deleteChair(it) }
        }
    }

}