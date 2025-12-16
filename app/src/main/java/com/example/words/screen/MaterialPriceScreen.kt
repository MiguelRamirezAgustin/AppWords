package com.example.words.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.words.Model.MaterialPriceViewModel
import com.example.words.db.model.MaterialPrice
import com.example.words.screen.component.ButtonMaterialLine
import com.example.words.screen.component.CustomTopAppBar


@Composable
fun MaterialPriceScreen(
    navController: NavController,
    viewModel: MaterialPriceViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        CustomTopAppBar(
            title = "Precio productos",
            onBackClick = { navController.popBackStack()}
        )
    },
        content = { paddingValues ->
            MaterialBody(paddingValues, viewModel)
        })
}

@Composable
fun MaterialBody(paddingValues: PaddingValues, viewModel: MaterialPriceViewModel) {
    var tubo34_20 = remember { mutableStateOf("") }
    var tubo34_18 = remember { mutableStateOf("") }
    var tubo12_20 = remember { mutableStateOf("") }
    var tubo12_18 = remember { mutableStateOf("") }
    var tubo78_20 = remember { mutableStateOf("") }
    var tubo78_18 = remember { mutableStateOf("") }
    var tubo1_20 = remember { mutableStateOf("") }
    var tubo1_18 = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(Color.White)
    ) {
        Spacer(Modifier.height(30.dp))
        PrecioPorMetroScreens(
            isTex = "Tb 1 C-18",
            istextField = tubo1_18
        )
        PrecioPorMetroScreens(
            isTex = "Tb 1 C-20",
            istextField = tubo1_20
        )
        PrecioPorMetroScreens(
            isTex = "Tb 7/8 C-18",
            istextField = tubo78_20
        )
        PrecioPorMetroScreens(
            isTex = "Tb 7/8 C-20",
            istextField = tubo78_18
        )
        PrecioPorMetroScreens(
            isTex = "Tb 3/4 C-18",
            istextField = tubo34_20
        )
        PrecioPorMetroScreens(
            isTex = "Tb 3/4 C-20",
            istextField = tubo34_18
        )
        PrecioPorMetroScreens(
            isTex = "Tb 1/2 C-18",
            istextField = tubo12_20
        )
        PrecioPorMetroScreens(
            isTex = "Tb 1/2 C-20",
            istextField = tubo12_18
        )

        ButtonMaterialLine(
            title = "Guardar",
            eventBtn = {
                val newMaterial = MaterialPrice(
                    tb_1_c18 = tubo1_18.value.toIntOrNull() ?: 0,
                    tb_1_c20 = tubo1_20.value.toIntOrNull() ?: 0,
                    tb_78_c18 = tubo78_18.value.toIntOrNull() ?: 0,
                    tb_78_c20 = tubo78_20.value.toIntOrNull() ?: 0,
                    tb_34_c18 = tubo34_18.value.toIntOrNull() ?: 0,
                    tb_34_c20 = tubo34_20.value.toIntOrNull() ?: 0,
                    tb_12_c18 = tubo12_18.value.toIntOrNull() ?: 0,
                    tb_12_c20 = tubo12_20.value.toIntOrNull() ?: 0
                )
                viewModel.insertMaterialPrice(newMaterial)
            }
        )
    }
}


@Composable
fun PrecioPorMetroScreens(isTex: String, istextField: MutableState<String>) {

    Row(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(text = isTex, color = Color.Black, fontSize = 16.sp, modifier = Modifier.weight(2f))
        OutlinedTextField(
            value = istextField.value,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    istextField.value = if (input.isEmpty()) "0" else input
                    istextField.value = input
                }
            },
            modifier = Modifier
                .weight(2f)
                .align(Alignment.CenterVertically),
            placeholder = { Text("Agregar precio") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}
