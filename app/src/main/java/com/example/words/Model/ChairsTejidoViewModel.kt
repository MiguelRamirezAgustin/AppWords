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
import com.example.words.Repository.ChairsTedijoRepository
import com.example.words.core.TextFieldState
import com.example.words.db.model.Chairs
import com.example.words.db.model.ChairsTejido
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ChairsTejidoViewModel @Inject constructor(
    private val repository: ChairsTedijoRepository
) : ViewModel() {



    val all: LiveData<List<ChairsTejido>> = repository.allChairTejido()

    fun insertChairTejido(
        sillaGrnade: Int,
        sillaChica: Int,
        sillaindividual: Int,
        bancos: Int,
        cuadrados: Int,
        silla_mini: Int,
        total: String,
        nota:String
    ) {
        viewModelScope.launch {
            val newChairTejido = ChairsTejido(
                null,
                sillaGrande =  sillaGrnade.toString(),
                sillaChica =   sillaChica.toString(),
                sillaIndividual =  sillaindividual.toString(),
                bancos = bancos.toString(),
                cuadrados =cuadrados.toString(),
                cuadrado_mini = silla_mini.toString(),
                total = total,
                nota = nota,
                update = Date()
            )
            repository.insertChairsTejido(newChairTejido)
        }
    }

    fun deleteChair(chair: Int?) {
        viewModelScope.launch {
            chair?.let { repository.deleteChairTejido(it) }
        }
    }

}