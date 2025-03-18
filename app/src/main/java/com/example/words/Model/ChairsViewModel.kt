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
import com.example.words.db.WeeksDatabase
import com.example.words.db.model.Chairs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date

class ChairsViewModel(application: Application) : ViewModel() {

    private val repository: ChairsRepository

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _text = mutableStateOf(TextFieldState())
    val text: State<TextFieldState> = _text

    val all: LiveData<List<Chairs>>
    var openDialog by mutableStateOf(false)
    private var currentId: Int? = null

    init {
        val db = WeeksDatabase.getInstance(application)
        val dao = db.chairsDao()
        repository = ChairsRepository(dao)
        all = repository.allChair()
    }

    private fun load(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                repository.findByIdChair(id).also { chairs ->
                    currentId = chairs.id
                    _text.value = text.value.copy(
                        text = chairs.papelera
                    )
                }
            } else {
                currentId = null
                _text.value = text.value.copy(
                    text = "text"
                )
            }
        }
    }

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

    fun onEvent(event: Event) {
        when (event) {
            is Event.SetText -> {
                _text.value = text.value.copy(
                    text = event.text
                )
            }

            is Event.Save -> {
                if (currentId != null) {
                    repository.updateChair(
                        Chairs(
                            currentId,
                            text.value.text,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            Date()
                        )
                    )
                } else {
                    repository.insertChairs(
                        Chairs(
                            null, text.value.text,
                            "",
                            "",
                            "",
                            "",
                            "", "", Date()
                        )
                    )
                }
                openDialog = false
                coroutineScope.launch(Dispatchers.IO) {
                    _eventFlow.emit(Event.Save)
                }
            }


            is Event.Delete -> {
                event.id?.let { repository.deleteChair(it) }
            }

            else -> {}
        }
    }
}