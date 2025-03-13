package com.example.words.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.words.Model.Event
import com.example.words.ui.theme.LightBrown
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.tickColor

@Composable
fun WorkScreen(navController: NavController) {

    Scaffold(
        topBar = {},
        floatingActionButton = {},
        content = { paddingValues ->
            // Contenido principal de la pantalla
            maincontent(paddingValues)
        }
    )
}


@Composable
fun maincontent(paddingValues: PaddingValues) {
    var isTotal by remember { mutableStateOf(0.0) }
    val formData = remember {
        mutableStateMapOf(
            "Mecedora Grande" to "",
            "Mecedora Chica" to "",
            "Silla individual" to "",
            "Papelera" to "",
            "Listonero" to ""
        )
    }

    fun calcularTotal() {
        isTotal = formData.entries.sumOf { (key, value) ->
            val cantidad = value.toIntOrNull() ?: 0
            when (key) {
                "Mecedora Grande" -> cantidad * 33.0
                "Mecedora Chica" -> cantidad * 33.0
                "Silla individual" -> cantidad * 28.0
                "Papelera" -> cantidad * 19.5
                "Listonero" -> cantidad * 18.0
                else -> 0.0
            }
        }
    }

    Column(modifier = Modifier.padding(paddingValues)) {
        Column(
            modifier = Modifier.padding(top = 30.dp, bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Precios:", fontSize = 30.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text("* Mecedora Grande: $33", fontSize = 16.sp)
                Text("* Mecedora Chica: $33", fontSize = 16.sp)
                Text("* Sillas Individual: $28", fontSize = 16.sp)
                Text("* Papelero: $19.5", fontSize = 16.sp)
                Text("* Listonero: $18", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    formData.keys.forEach { key ->
                        EditableInfoRow(
                            label = key,
                            value = formData[key] ?: "",
                            onValueChange = { newValue ->
                                Log.d("Print key ========>", " key: ${key}")
                                Log.d("Print key ========>", " formData: ${formData[key]}")
                                Log.d("Print key ========>", " newValue: ${newValue}")
                                formData[key] = newValue

                                calcularTotal()
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BtnCustoms(
                            text = "Guardar",
                            onClick = {
                                formData.forEach { (key, value) ->
                                    Log.d(
                                        "Print key ========>",
                                        " Screeen::${key} Resul: ${value}"
                                    )
                                }
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
            Text(
                modifier = Modifier
                    .padding(top = 15.dp, end = 20.dp, start = 20.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Total: $ ${isTotal}",
                fontSize = 24.sp
            )
        }

    }
}


@Composable
fun EditableInfoRow(label: String, value: String, onValueChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = blue,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(2f)
        )
        OutlinedTextField(
            value = value,
            singleLine = true,
            onValueChange = {
                Log.d("Valor", "number: $it")
                onValueChange(it)
            },
            modifier = Modifier.weight(1f).background(Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

