package com.example.words.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.words.Repository.MaterialPriceRepository
import com.example.words.db.model.MaterialPrice
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaterialPriceViewModel @Inject constructor(
    private val repository: MaterialPriceRepository
) : ViewModel() {

    val all: LiveData<List<MaterialPrice>> = repository.allMaterialPrice()

    fun insertMaterialPrice(
        materialPrice:MaterialPrice
    ) {
        viewModelScope.launch {

            repository.insertMaterialPrice(materialPrice)
        }
    }



    fun updateMaterialPrice(chair: MaterialPrice) {
        viewModelScope.launch {
            chair?.let { repository.updateMaterialPrice(it) }
        }
    }


    fun deleteMaterialPrice(id: Int?) {
        viewModelScope.launch {
            id?.let { repository.deleteMaterialPrice(it) }
        }
    }


}