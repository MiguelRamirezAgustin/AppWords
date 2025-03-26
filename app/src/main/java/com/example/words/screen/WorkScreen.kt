package com.example.words.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.words.Model.ChairsViewModel
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.tickColor

@Composable
fun WorkScreen(navController: NavController, viewModel: ChairsViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Semana",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
            )
        },
        floatingActionButton = {},
        content = { paddingValues ->
            // Contenido principal de la pantalla
            maincontent(viewModel, paddingValues)

        }
    )
}


@Composable
fun maincontent(
    viewModel: ChairsViewModel,
    paddingValues: PaddingValues,
) {
    var showToast by remember { mutableStateOf(false) }

    var isTotal by remember { mutableStateOf(0.0) }
    val formData = remember {
        mutableStateMapOf(
            "Papelera" to "",
            "Listonero" to "",
            "Botaneros" to "",
            "Mecedora G" to "",
            "Silla ind" to "",
            "Mecedora Ch" to "",
        )
    }

    fun calcularTotal() {
        isTotal = formData.entries.sumOf { (key, value) ->
            val cantidad = value.toIntOrNull() ?: 0
            when (key) {
                "Mecedora G" -> cantidad * 33.0
                "Mecedora Ch" -> cantidad * 33.0
                "Silla ind" -> cantidad * 28.0
                "Papelera" -> cantidad * 19.5
                "Listonero" -> cantidad * 18.0
                "Botaneros" -> cantidad * 19.0
                else -> 0.0
            }
        }
    }

    Column(modifier = Modifier.padding(paddingValues)) {
        Column(
            modifier = Modifier
                .padding(top = 5.dp, bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(2f)) {
                        Row {
                            Text("Mecedora G.", color = blue, fontSize = 18.sp)
                            Text(
                                " : $33", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                        Row {
                            Text("Mecedora Ch.", color = blue, fontSize = 18.sp)
                            Text(
                                " : $33", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text("Sillas Ind.", color = blue, fontSize = 18.sp)
                            Text(
                                " : $28", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Column(modifier = Modifier.weight(2f)) {

                        Row {
                            Text("Papelero", color = blue, fontSize = 18.sp)
                            Text(
                                " : $19.5", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text("Listonero", color = blue, fontSize = 18.sp)
                            Text(
                                " : $18", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )

                        }
                        Row {
                            Text("Botanero", color = blue, fontSize = 18.sp)
                            Text(
                                " : $19", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )

                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, end = 20.dp, start = 20.dp)
                        .align(Alignment.CenterHorizontally),
                    text = "Total: $ ${isTotal}",
                    fontSize = 24.sp
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp, start = 5.dp, top = 10.dp)
                ) {
                    formData.keys.chunked(2).forEach { key ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            key.forEach { item ->
                                Column(
                                    modifier = Modifier.weight(1f),
                                    verticalArrangement = Arrangement.Center
                                ) {

                                    EditableInfoRow(
                                        label = item,
                                        value = formData[item] ?: "",
                                        onValueChange = { newValue ->
                                            Log.d("Print key ========>", " key: ${key}")
                                            Log.d(
                                                "Print key ========>",
                                                " formData: ${formData[item]}"
                                            )
                                            Log.d("Print key ========>", " newValue: ${newValue}")
                                            formData[item] = newValue
                                            calcularTotal()
                                        }
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        BtnCornerRow(
                            title = "Guardar",
                            onClick = {
                                viewModel.insertChair(
                                    sillaGrnade = formData["Mecedora G"]?.toIntOrNull() ?: 0,
                                    sillaChica = formData["Mecedora Ch"]?.toIntOrNull() ?: 0,
                                    sillaindividual = formData["Silla ind"]?.toIntOrNull() ?: 0,
                                    papelera = formData["Papelera"]?.toIntOrNull() ?: 0,
                                    listonero = formData["Listonero"]?.toIntOrNull() ?: 0,
                                    botanero = formData["Botaneros"]?.toIntOrNull() ?: 0,
                                    sueldo = isTotal.toString()
                                )
                                showToast = true

                            },
                            style = TextStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .height(52.dp).width(150.dp),
                            colorBorder = tickColor,
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 0.dp
                            )
                        )

                        BtnCornerRow(
                            title = "Limpiar",
                            onClick = {
                                formData.keys.forEach { key ->
                                    formData[key] = "" // Establece cada campo como vacío
                                }
                                calcularTotal()
                            },
                            style = TextStyle(
                                color = tickColor,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .height(52.dp).width(150.dp),
                            colorBorder = tickColor,
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 0.dp
                            )
                        )
                    }

                }
            }
            if (showToast) {
                ShowToast("¡Hola desde Compose!")
                showToast = false // Restablecer para evitar repetir el toast
            }
        }

    }
}


@Composable
fun EditableInfoRow(label: String, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = blue,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = blue,
                backgroundColor = Color.White,
                focusedBorderColor = tickColor

            ),
            textStyle = TextStyle(
                fontSize = 18.sp, // Cambia el tamaño del texto aquí
                color = Color.Black // Opcional: Cambia el color del texto
            ),
            value = value,
            singleLine = true,
            onValueChange = {
                Log.d("Valor", "number: $it")
                onValueChange(it)
            },
            modifier = Modifier
                .weight(1f)
                .height(58.dp)
                .background(Color.White),

            label = { androidx.compose.material3.Text("Piezas") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

