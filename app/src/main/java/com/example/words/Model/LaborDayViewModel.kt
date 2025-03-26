package com.example.words.Model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words.Repository.LaborDayRepository
import com.example.words.core.TextFieldState

import com.example.words.db.model.LaborDay
import com.example.words.db.model.Paint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class LaborDayViewModel @Inject constructor(private val repository: LaborDayRepository) : ViewModel() {



    private val _text = mutableStateOf(TextFieldState())
    val text: State<TextFieldState> = _text
    val all: LiveData<List<LaborDay>> = repository.all()
    var openDialog by mutableStateOf(false)
    private var currentId: Int? = null


    fun deleteItemLaborDay(chair: Int?) {
        viewModelScope.launch {
            chair?.let { repository.delete(it) }
        }
    }

    fun insertLaborDay(
        textHours: String,
    ) {
        viewModelScope.launch {
            val newPaint = LaborDay(
                null,
                text = textHours,
                update = Date()
            )
            repository.insert(newPaint)
        }
    }



}