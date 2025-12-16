package com.example.words.Repository

import androidx.lifecycle.LiveData
import com.example.words.db.MaterialPriceDAO
import com.example.words.db.model.MaterialPrice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MaterialPriceRepository @Inject constructor(private val materialPrice: MaterialPriceDAO) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    suspend  fun insertMaterialPrice(material: MaterialPrice) {
        coroutineScope.launch(Dispatchers.IO) {
            materialPrice.insert(material)
        }
    }

    fun updateMaterialPrice(material: MaterialPrice) {
        coroutineScope.launch(Dispatchers.IO) {
            materialPrice.update(material)
        }
    }

    fun allMaterialPrice(): LiveData<List<MaterialPrice>> {
        return materialPrice.all()
    }


    fun deleteMaterialPrice(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            materialPrice.delete(id)
        }
    }



}