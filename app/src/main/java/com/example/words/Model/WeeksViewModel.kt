package com.example.words.Model

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words.Repository.WeeksRepository
import com.example.words.core.TextFieldState
import com.example.words.db.WeeksDatabase
import com.example.words.db.model.Weeks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date

class WeeksViewModel (application: Application): ViewModel() {

    private val repository: WeeksRepository

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _text = mutableStateOf(TextFieldState())
    val text: State<TextFieldState> = _text

    val all: LiveData<List<Weeks>>
    var openDialog by mutableStateOf(false)
    private var currentId: Int? = null

    init {
        val db = WeeksDatabase.getInstance(application)
        val dao = db.notesDao()
        repository = WeeksRepository(dao)

        all = repository.all()
    }

    private fun load(id: Int?){
        viewModelScope.launch {
            if (id != null) {
                repository.findById(id).also { note ->
                    currentId = note.id
                    _text.value = text.value.copy(
                        text = note.text
                    )
                }
            }else{
                currentId = null
                _text.value = text.value.copy(
                    text = "text"
                )
            }
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
                if(currentId != null){
                    repository.update(Weeks(currentId, text.value.text, Date()))
                }else{
                    repository.insert(Weeks(null, text.value.text, Date()))
                }
                openDialog = false
                coroutineScope.launch(Dispatchers.IO) {
                    _eventFlow.emit(Event.Save)
                }
            }
            is Event.OpenDialog -> {
                openDialog = true
            }
            is Event.CloseDialog -> {
                openDialog = false
            }
            is Event.Load -> {
                load(event.id)
                openDialog = true
            }
            is Event.Delete -> {
                event.id?.let { repository.delete(it) }
            }
        }
    }
}