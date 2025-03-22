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
import com.example.words.db.WeeksDatabase
import com.example.words.db.model.Chairs
import com.example.words.db.model.Paint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date


class PaintViewModel(application: Application) : ViewModel() {

    private val repository: PaintRepository

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _text = mutableStateOf(TextFieldState())
    val text: State<TextFieldState> = _text

    val all: LiveData<List<Paint>>
    var openDialog by mutableStateOf(false)
    private var currentId: Int? = null

    init {
        val db = WeeksDatabase.getInstance(application)
        val dao = db.paintDao()
        repository = PaintRepository(dao)
        all = repository.allPaint()
    }

    private fun load(id: Int?) {
        viewModelScope.launch {
            if (id != null) {
                repository.findByIdPaint(id).also { paint ->
                    currentId = paint.id
                    _text.value = text.value.copy(
                        text = paint.papelera
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

    fun onEvent(event: Event) {
        when (event) {
            is Event.SetText -> {
                _text.value = text.value.copy(
                    text = event.text
                )
            }

            is Event.Save -> {
                if (currentId != null) {
                    repository.updatePaint(
                        Paint(
                            currentId,
                            text.value.text,
                            "",
                            "",
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
                    repository.insertPaint(
                        Paint(
                            null, text.value.text,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "", "", "", Date()
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