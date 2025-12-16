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
import com.example.words.Model.ChairsTejidoViewModel
import com.example.words.navigation.Screen
import com.example.words.screen.component.BtnCornerRow
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.tickColor
import com.example.words.ui.theme.white

@Composable
fun ChairsTejidoScreen(navController: NavController, viewModel: ChairsTejidoViewModel = hiltViewModel()) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tedijo",
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
            maincontentChair(viewModel, paddingValues,navController)

        }
    )
}


@Composable
fun maincontentChair(
    viewModel: ChairsTejidoViewModel,
    paddingValues: PaddingValues,
    navController: NavController,
) {
    var showToast by remember { mutableStateOf(false) }
    var texts by remember { mutableStateOf("") }
    var isTotal by remember { mutableStateOf(0.0) }
    val formData = remember {
        mutableStateMapOf(
            "Cuadrados" to "",
            "Silla Min" to "",
            "Bancos" to "",
            "Mecedora G" to "",
            "Silla ind" to "",
            "Mecedora Ch" to "",
        )
    }

    fun calcularTotal() {
        isTotal = formData.entries.sumOf { (key, value) ->
            val cantidad = value.toIntOrNull() ?: 0
            when (key) {
                "Mecedora G" -> cantidad * 40.0
                "Mecedora Ch" -> cantidad * 30.0
                "Silla ind" -> cantidad * 30.0
                "Bancos" -> cantidad * 50.0
                "Silla Min" -> cantidad * 15.0
                "Cuadrados" -> cantidad * 40.0
                else -> 0.0
            }
        }
    }

    val tieneAlMenosUnValor = formData.values.any { value ->
        val cantidad = value.toIntOrNull() ?: 0
        cantidad > 0
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
                                " : $40", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                        Row {
                            Text("Mecedora Ch.", color = blue, fontSize = 18.sp)
                            Text(
                                " : $30", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text("Sillas Ind.", color = blue, fontSize = 18.sp)
                            Text(
                                " : $30", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                    Column(modifier = Modifier.weight(2f)) {

                        Row {
                            Text("Bancos", color = blue, fontSize = 18.sp)
                            Text(
                                " : $50", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text("Silla Min", color = blue, fontSize = 18.sp)
                            Text(
                                " : $15", color = blue, fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )

                        }
                        Row {
                            Text("Cuadrados", color = blue, fontSize = 18.sp)
                            Text(
                                " : $40", color = blue, fontSize = 18.sp,
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

                    Column {
                        OutlinedTextField(
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = blue,
                                backgroundColor = Color.White,
                                focusedBorderColor = tickColor

                            ),
                            value = texts,
                            onValueChange = {
                                Log.d("Print Log ========>", " Screeen::${it}. texts::${texts} ")
                                if (!it.isEmpty()) {
                                    texts = it
                                }

                            },
                            modifier = Modifier
                                .padding( end = 10.dp, start = 10.dp, bottom = 10.dp)
                                .fillMaxWidth()
                                .height(65.dp)
                                .background(Color.White),
                            textStyle = TextStyle(
                                fontSize = 24.sp, // Cambia el tamaño del texto aquí
                                color = Color.Black // Opcional: Cambia el color del texto
                            ),
                            label = { Text( text ="Nota") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )
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
                                viewModel.insertChairTejido(
                                    sillaGrnade = formData["Mecedora G"]?.toIntOrNull() ?: 0,
                                    sillaChica = formData["Mecedora Ch"]?.toIntOrNull() ?: 0,
                                    sillaindividual = formData["Silla ind"]?.toIntOrNull() ?: 0,
                                    bancos = formData["Bancos"]?.toIntOrNull() ?: 0,
                                    cuadrados = formData["Cuadrados"]?.toIntOrNull() ?: 0,
                                    silla_mini = formData["Silla Min"]?.toIntOrNull() ?: 0,
                                    total = isTotal.toString(),
                                    nota = texts
                                )
                                showToast = true
                                texts = ""
                                navController.navigate(Screen.ListChairsTedijo.route)
                            },
                            style = TextStyle(
                                color = if (tieneAlMenosUnValor) white else Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .height(52.dp).width(150.dp),
                            colorBorder = tickColor,
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 0.dp
                            ),
                            enabled = tieneAlMenosUnValor
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
                                color = white,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .height(52.dp).width(150.dp),
                            colorBorder = tickColor,
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 0.dp
                            ),
                            enabled = true
                        )
                    }

                }
            }

        }

    }
}

