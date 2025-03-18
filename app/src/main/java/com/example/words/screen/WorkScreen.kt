package com.example.words.screen

import android.app.Application
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.words.Model.ChairsViewModel
import com.example.words.Model.Event
import com.example.words.Model.WeeksViewModel
import com.example.words.Model.WeksViewModelFactory
import com.example.words.R
import com.example.words.db.model.Chairs
import com.example.words.navigation.Screen
import com.example.words.ui.theme.LightBrown
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.tickColor

@Composable
fun WorkScreen( navController: NavController,viewModel: ChairsViewModel) {

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
                    IconButton(onClick = {navController.popBackStack()}) {
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
            maincontent(viewModel,paddingValues, navController)

        }
    )
}


@Composable
fun maincontent( viewModel: ChairsViewModel,  paddingValues: PaddingValues, navController: NavController) {


    var isTotal by remember { mutableStateOf(0.0) }
    val formData = remember {
        mutableStateMapOf(
            "Mecedora Grande" to "",
            "Mecedora Chica" to "",
            "Silla individual" to "",
            "Papelera" to "",
            "Listonero" to "",
            "Botaneros" to ""
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
                "Botaneros" -> cantidad * 19.0
                else -> 0.0
            }
        }
    }

    Column(modifier = Modifier.padding(paddingValues)) {
        Column(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row {
                            Text("Mecedora Grande", color = blue, fontSize = 16.sp)
                            Text(
                                " : $33", color = blue, fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Row {
                            Text("Mecedora Chica", color = blue, fontSize = 16.sp)
                            Text(
                                " : $33", color = blue, fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row {
                            Text("Sillas Individual", color = blue, fontSize = 16.sp)
                            Text(
                                " : $28", color = blue, fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row {
                            Text("Papelero", color = blue, fontSize = 16.sp)
                            Text(
                                " : $19.5", color = blue, fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Row {
                            Text("Listonero", color = blue, fontSize = 16.sp)
                            Text(
                                " : $18", color = blue, fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }
                        Row {
                            Text("Botanero", color = blue, fontSize = 16.sp)
                            Text(
                                " : $19", color = blue, fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Image(
                            painter = painterResource(id = R.drawable.soldador),
                            contentDescription = "image description",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .width(80.dp)
                                .height(80.dp)
                                .align(Alignment.CenterHorizontally)

                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

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

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BtnCustoms(
                            text = "Guardar",
                            onClick = {
                                viewModel.insertChair(
                                    sillaGrnade = formData["Mecedora Grande"]?.toIntOrNull() ?: 0,
                                    sillaChica = formData["Mecedora Chica"]?.toIntOrNull() ?: 0,
                                    sillaindividual = formData["Silla individual"]?.toIntOrNull() ?: 0,
                                    papelera = formData["Papelera"]?.toIntOrNull() ?: 0,
                                    listonero = formData["Listonero"]?.toIntOrNull() ?: 0,
                                    botanero = formData["Botaneros"]?.toIntOrNull() ?: 0,
                                    sueldo = isTotal.toString()
                                )

                                navController.navigate(Screen.ListChairs.route)

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
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = blue,
                backgroundColor = Color.White,
                focusedBorderColor = tickColor

            ),
            textStyle = TextStyle(
                fontSize = 12.sp, // Cambia el tamaño del texto aquí
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
                .height(55.dp)
                .background(Color.White),

            label = { androidx.compose.material3.Text("Piezas") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

