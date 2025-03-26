package com.example.words.screen

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavController
import com.example.words.Model.Event
import com.example.words.Model.LaborDayViewModel
import com.example.words.db.model.LaborDay
import com.example.words.ui.theme.LightBrown
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.tickColor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LaborDayScreen(viewModel: LaborDayViewModel = hiltViewModel(), navController: NavController) {
    CrudScreenSetup(viewModel, navController)
}




@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CrudScreenSetup(viewModel: LaborDayViewModel, navController: NavController) {
    var texts by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        topBar = { },
        floatingActionButtonPosition = FabPosition.End,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            Column(
                Modifier
                    .padding(start = 24.dp, top = 29.dp, end = 24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Agregar nuevo dia",
                    fontSize = 30.sp,
                    color = blue,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
                )

                Row(modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)) {
                    androidx.compose.material.Text("Hora", color = blue, fontSize = 18.sp)
                    androidx.compose.material.Text(
                        " : $28", color = blue, fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = blue,
                        backgroundColor = Color.White,
                        focusedBorderColor = tickColor

                    ),
                    value = texts,
                    onValueChange = {
                        // Filtrar solo los caracteres numéricos y limitar la longitud
                        val filteredText = it.filter { char -> char.isDigit() }.take(2)
                        texts = filteredText
                        Log.d("Print Log ========>", " Screeen::${it} ")
                        if (!it.isEmpty()) {
                            viewModel.insertLaborDay(it)
                        }

                    },
                    label = { Text("Horas") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )


            }
            Spacer(modifier = Modifier.padding(bottom = 30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 24.dp,
                        start = 24.dp,
                        end = 24.dp,
                        top = 10.dp
                    ), horizontalArrangement = Arrangement.SpaceBetween
            ) {

                BtnCornerRow(
                    title = "Guardar",
                    onClick = {
                        navController.popBackStack()
                    },
                    style = TextStyle(
                        color = tickColor,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .height(52.dp)
                        .width(150.dp),
                    colorBorder = tickColor,
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 0.dp
                    )
                )
                BtnCornerRow(
                    title = "Cancelar",
                    onClick = {
                        navController.popBackStack()
                    },
                    style = TextStyle(
                        color = tickColor,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .height(52.dp)
                        .width(150.dp),
                    colorBorder = tickColor,
                    elevation = ButtonDefaults.elevatedButtonElevation(
                        defaultElevation = 0.dp
                    )
                )

            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CrudScreen(
    all: List<LaborDay>,
    openDialog: Boolean,
    onEvent: (Event) -> Unit,
    onEventNavigate: () -> Unit,
    onEventNavigateList: () -> Unit
) {
    val scrollState = rememberScrollState()
    var ishours = 0
    all.forEach { action ->
        ishours += convertirAEntero(cadena = action.text)
    }

    Box(
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .padding()
                .fillMaxSize()
                .height(40.dp)
        ) {
            LazyColumn {

                item {
                    Row(
                        Modifier
                            .padding(start = 25.dp, top = 30.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(2f)
                        ) {
                            Text(
                                "Horas: " + ishours, color = blue, fontSize = 28.sp,
                                fontWeight = FontWeight.Normal
                            )
                            Text(
                                "Pago: $ " + ishours * 28, color = blue, fontSize = 25.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }

                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                        }
                    }
                }

                items(all) {
                    Card(
                        modifier = Modifier
                            .padding(end = 24.dp, start = 24.dp, top = 15.dp, bottom = 6.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {

                            }
                            .height(90.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = 9.dp,
                        backgroundColor = Color.LightGray
                    ) {

                        Log.d("Date--->", "${it.update.toString()}")
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(150.dp)
                                    .background(Color.White)
                                    .verticalScroll(scrollState)
                                    .padding(16.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    modifier = Modifier.padding(
                                        start = 8.dp,
                                        top = 5.dp,
                                        end = 15.dp
                                    ),
                                    textAlign = TextAlign.Start,
                                    text = FormatearFecha(it.update.toString()),
                                    color = blue,
                                    fontSize = 16.sp,
                                    lineHeight = 23.sp,
                                    style = TextStyle.Default,
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White)
                            ) {
                                Text(
                                    modifier = Modifier.padding(
                                        start = 8.dp,
                                        top = 15.dp,
                                        bottom = 15.dp
                                    ),
                                    textAlign = TextAlign.Start,
                                    text = "Horas: " + it.text,
                                    color = blue,
                                    fontSize = 24.sp,
                                    lineHeight = 24.sp,
                                    style = TextStyle.Default,
                                )

                                Row(
                                    Modifier
                                        .padding(bottom = 10.dp, start = 5.dp)
                                        .fillMaxWidth()
                                        .background(Color.White),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.Bottom,
                                ) {
                                    IconButton(
                                        modifier = Modifier
                                            .size(50.dp),
                                        colors = IconButtonColors(
                                            containerColor = Color.Transparent,
                                            contentColor = Color.Red,
                                            disabledContentColor = Color.White,
                                            disabledContainerColor = Color.Red
                                        ),
                                        onClick = {
                                            onEvent(Event.Delete(it.id))
                                        }) {
                                        Icon(Icons.Rounded.Delete, contentDescription = null)
                                    }
                                    IconButton(modifier = Modifier
                                        .size(50.dp),
                                        colors = IconButtonColors(
                                            containerColor = Color.Transparent,
                                            contentColor = Color.Blue,
                                            disabledContentColor = Color.White,
                                            disabledContainerColor = Color.Red
                                        ), onClick = {
                                            onEvent(Event.Load(it.id))
                                        }) {
                                        Icon(Icons.Rounded.Edit, contentDescription = null)
                                    }
                                }

                            }
                        }
                    }

                }

            }
        }


    }

    AlerDialogPopupAdd(openDialog = openDialog, onEvent = onEvent)
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormatearFecha(fechaOriginal: String): String {
    // Definir el patrón del formato de la fecha original
    val formatoEntrada = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    // Definir el patrón del formato de la fecha deseada
    val formatoSalida =
        DateTimeFormatter.ofPattern("eee / mm / yy '\n' hh:mm a", Locale("es", "MX"))

    // Parsear la fecha original al objeto LocalDateTime
    val fecha = remember { LocalDateTime.parse(fechaOriginal, formatoEntrada) }
    // Formatear la fecha al formato deseado
    val fechaFormateada = remember { fecha.format(formatoSalida) }

    // Mostrar la fecha formateada en un componente Text

    return fechaFormateada
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormatearFechaDay(fechaOriginal: String): String {
    // Definir el patrón del formato de la fecha original
    val formatoEntrada = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    // Definir el patrón del formato de la fecha deseada
    val formatoSalida =
        DateTimeFormatter.ofPattern("EEE/mm/yy", Locale("es", "MX"))

    // Parsear la fecha original al objeto LocalDateTime
    val fecha = remember { LocalDateTime.parse(fechaOriginal, formatoEntrada) }
    // Formatear la fecha al formato deseado
    val fechaFormateada = remember { fecha.format(formatoSalida) }

    // Mostrar la fecha formateada en un componente Text

    return fechaFormateada
}

fun convertirAEntero(cadena: String?): Int {
    return cadena?.toIntOrNull() ?: 0
}