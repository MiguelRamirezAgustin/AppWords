package com.example.words.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.words.Model.LaborDayViewModel
import com.example.words.ui.theme.LightBrown
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.words.R
import com.example.words.screen.component.BtnCustoms
import com.example.words.ui.theme.blue
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalTime
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LaborDayScreen(viewModel: LaborDayViewModel = hiltViewModel(), navController: NavController) {
    CrudScreenSetup(viewModel, navController)
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrudScreenSetup(viewModel: LaborDayViewModel, navController: NavController) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }
    val horarios = remember { mutableStateListOf<Pair<LocalTime, LocalTime>>() }
    var entrada by remember { mutableStateOf(LocalTime.of(9, 0)) }
    var salida by remember { mutableStateOf(LocalTime.of(17, 0)) }
    val context = LocalContext.current
    val formatoHora = DateTimeFormatter.ofPattern("hh:mm a", Locale("es", "MX"))

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    androidx.compose.material.Text(
                        text = "Horas de trabajo",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {
                        androidx.compose.material.Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar horario")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 15.dp),
                text = "Lista de horarios",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            val totalDuracion = horarios.fold(Duration.ZERO) { acc, (entrada, salida) ->
                acc.plus(Duration.between(entrada, salida))
            }
            val totalHoras = totalDuracion.toHours()
            val totalMinutos = totalDuracion.toMinutes() % 60

            Text(
                text = "Total acumulado: ${totalHoras} h ${totalMinutos} m",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                color = blue,
                modifier = Modifier.padding(vertical = 10.dp).align(Alignment.CenterHorizontally)
            )


            horarios.forEachIndexed { index, (entrada, salida) ->
                val duracion = Duration.between(entrada, salida)
                val horas = duracion.toHours()
                val minutos = duracion.toMinutes() % 60
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp, start = 10.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(56.dp)
                    ) {
                        Text(
                            text = "Ent: ${entrada.format(formatoHora)} - Sal: ${
                                salida.format(
                                    formatoHora
                                )
                            }",
                            color = Color.Black,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(),
                        )

                        IconButton(modifier = Modifier.padding(start = 18.dp),
                            onClick = { horarios.removeAt(index) }) {
                            Image(
                                painter = painterResource(id = R.drawable.alarm),
                                modifier = Modifier,
                                contentDescription = "Eliminar"
                            )
                        }
                    }

                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            text = "Total: ${horas} h ${minutos} m",
                            fontSize = 22.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(),
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Selecciona tus horarios",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Blue
                    )

                    Spacer(Modifier.height(16.dp))
                    IsBtn(isText = "Entrada: ${entrada.format(formatoHora)}", onClick = {
                        showTimePicker(
                            context = context,
                            initial = entrada,
                            onTimeSelected = { entrada = it }
                        )
                    })

                    Spacer(Modifier.height(12.dp))
                    IsBtn(isText = "Salida: ${salida.format(formatoHora)}", onClick = {
                        showTimePicker(
                            context = context,
                            initial = salida,
                            onTimeSelected = { salida = it }
                        )
                    })

                    Spacer(Modifier.height(24.dp))

                    IsBtn(isText = "Aceptar", onClick = {
                        horarios.add(entrada to salida)
                        showSheet = false
                    })
                }
            }
        }
    }
}





@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FormatearFecha(fechaOriginal: String): String {
    // Definir el patrón del formato de la fecha original
    val formatoEntrada = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    // Definir el patrón del formato de la fecha deseada
    val formatoSalida =
        DateTimeFormatter.ofPattern("EEEE / MMMM ", Locale("es", "MX"))

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
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("MMM-dd", Locale.ENGLISH)

    val date: Date = inputFormat.parse(fechaOriginal)!!
    return outputFormat.format(date)
}

@RequiresApi(Build.VERSION_CODES.O)
fun parseCustomDate(dateStr: String): String {
    // Formato original del string
    val inputFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)

    // Parsear el string a ZonedDateTime
    val parsedDate = ZonedDateTime.parse(dateStr, inputFormatter)

    // Formatear a "Lunes/12/2025"
    val outputFormatter = DateTimeFormatter.ofPattern("EEEE/dd/yyyy", Locale("es", "ES"))

    return parsedDate.format(outputFormatter)
}

fun convertirAEntero(cadena: String?): Int {
    return cadena?.toIntOrNull() ?: 0
}


/*fun TimePickerComposable(
    initialHour: Int,
    initialMinute: Int,
    onTimeSelected: (Int, Int) -> Unit,
    onDismiss: () -> Unit = {}
) {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val dialog = TimePickerDialog(
            context,
            { _, hour: Int, minute: Int ->
                onTimeSelected(hour, minute)
            },
            initialHour,
            initialMinute,
            false
        )
        dialog.setOnDismissListener { onDismiss() }
        dialog.show()

        onDispose {
            dialog.dismiss()
        }
    }
}*/



@RequiresApi(Build.VERSION_CODES.O)
fun showTimePicker(
    context: Context,
    initial: LocalTime,
    onTimeSelected: (LocalTime) -> Unit
) {
    val dialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            onTimeSelected(LocalTime.of(hour, minute))
        },
        initial.hour,
        initial.minute,
        false // false = formato 12h (AM/PM), true = 24h
    )
    dialog.show()
}


