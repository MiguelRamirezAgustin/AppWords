package com.example.words.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
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
import androidx.navigation.NavController
import com.example.words.Model.ChairsViewModel
import com.example.words.Model.PaintViewModel
import com.example.words.navigation.Screen
import com.example.words.ui.theme.LightBrown
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.tickColor


@Composable
fun PaintScreen(navController: NavController, viewModel: PaintViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de pintura",
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
            maincontentListPainter(paddingValues, viewModelPaint = viewModel, onEventNavigate = {
                navController.navigate(Screen.ListPaint.route)
            })
        }
    )
}


@Composable
fun maincontentListPainter(
    paddingValues: PaddingValues, viewModelPaint: PaintViewModel,
    onEventNavigate: () -> Unit
) {
    var isTotal by remember { mutableStateOf(0.0) }
    var textNote by remember { mutableStateOf("") }

    val formData = remember {
        mutableStateMapOf(
            "Mecedora Grande $35" to "",
            "Mecedora Chica $25" to "",
            "Silla individual $33" to "",
            "Papelera $19" to "",
            "Listonero $25" to "",
            "Botaneros $26" to "",
            "Arañas $22" to ""
        )
    }

    fun calcularTotal() {
        isTotal = formData.entries.sumOf { (key, value) ->
            val cantidad = value.toIntOrNull() ?: 0
            when (key.substringBefore(" $")) {
                "Mecedora Grande" -> cantidad * 33.0
                "Mecedora Chica" -> cantidad * 33.0
                "Silla individual" -> cantidad * 28.0
                "Papelera" -> cantidad * 19.5
                "Listonero" -> cantidad * 18.0
                "Arañas" -> cantidad * 22.0
                "Botaneros" -> cantidad * 19.0
                else -> 0.0
            }
        }
    }


    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            modifier = Modifier
                .padding(top = 15.dp, end = 20.dp, start = 20.dp)
                .align(Alignment.CenterHorizontally),
            text = "Total: $ ${isTotal}",
            fontSize = 24.sp
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            formData.keys.forEach { key ->
                Log.d("Pintura key ========>", " formData: ${formData[key]}")
                EditableInfoRow(
                    label = key,
                    value = formData[key] ?: "",
                    onValueChange = { newValue ->
                        Log.d("Pintura key ========>", " key: ${key}")
                        Log.d("Pintura key ========>", " newValue: ${newValue}")
                        formData[key] = newValue
                        calcularTotal()
                    }
                )
            }

            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = blue,
                    backgroundColor = Color.White,
                    focusedBorderColor = tickColor

                ),
                textStyle = TextStyle(
                    fontSize = 12.sp, // Cambia el tamaño del texto aquí
                    color = Color.Black // Opcional: Cambia el color del texto
                ),
                value = textNote,
                singleLine = false,
                onValueChange = { newText ->
                    Log.d("Note", "newText: $newText")
                    textNote = newText
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.White),

                label = { Text("Nota") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BtnCustoms(
                    text = "Guardar",
                    onClick = {
                        viewModelPaint.insertPaint(
                            sillaGrande = formData["Mecedora Grande $35"]?.toIntOrNull() ?: 0,
                            sillaChica = formData["Mecedora Chica $25"]?.toIntOrNull() ?: 0,
                            sillaindividual = formData["Silla individual $33"]?.toIntOrNull() ?: 0,
                            papelera = formData["Papelera $19"]?.toIntOrNull() ?: 0,
                            listonero = formData["Listonero $25"]?.toIntOrNull() ?: 0,
                            botanero = formData["Botaneros $26"]?.toIntOrNull() ?: 0,
                            arana = formData["Araña $22"]?.toIntOrNull() ?: 0,
                            total = isTotal.toString(),
                            nota = textNote
                        )
                        onEventNavigate()
                    },
                    colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = Color.White,
                        contentColor = LightBrown,
                        containerColor = LightBrown

                    ),
                    modifier = Modifier
                        .height(52.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 0.dp
                    ),
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )

                BtnCustoms(
                    text = "Limpiar",
                    onClick = {
                        formData.keys.forEach { key ->
                            formData[key] = "" // Establece cada campo como vacío
                        }
                        textNote = ""
                        calcularTotal()
                    },
                    colors = ButtonDefaults.buttonColors(
                        disabledContainerColor = Color.White,
                        contentColor = tickColor,
                        containerColor = tickColor
                    ),
                    modifier = Modifier
                        .height(52.dp),
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 0.dp
                    ),
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}