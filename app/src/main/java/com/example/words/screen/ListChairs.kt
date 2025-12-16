package com.example.words.screen

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.words.Model.ChairsViewModel
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.progressBlue
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ListChairs(navController: NavController, viewModel: ChairsViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de sillas armados",
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
            maincontentList(viewModel, paddingValues)

        }
    )
}

@Composable
fun maincontentList(viewModel: ChairsViewModel, paddingValues: PaddingValues) {

    val context = LocalContext.current

    val precios = mapOf(
        "sillaGrande" to 33.0,
        "sillaChica" to 33.0,
        "papelera" to 19.5,
        "bancos" to 25.0,
        "sillaIndividual" to 28.0,
        "listonero" to 18.0,
        "listoneroCortado" to 10.0,
        "Botanero" to 19.0,
        "PortaGarrafon" to 18.0
    )



    val scrollState = rememberScrollState()
    val chairs by viewModel.all.observeAsState()  // Escuchar cambios en la BD
    Log.d("Print Log ========>", " Screeen::Resul: ${chairs}")
    Column(
        modifier = Modifier
            .padding(bottom = 30.dp, top = 10.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        chairs?.forEach { it ->
            Log.d("Print Log ========>", " Screeen: ${it.update.toString()}")

            Card(
                modifier = Modifier
                    .background(Color.White)
                    .padding(end = 15.dp, start = 15.dp, top = 10.dp, bottom = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {},
                contentColor = Color.White,
                border = BorderStroke(1.dp, progressBlue),
                shape = RoundedCornerShape(10.dp),
                elevation = 9.dp,
                backgroundColor = Color.White
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 10.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier,
                            text = formatFecha(it.update.toString()),
                            color = blue,
                            fontSize = 18.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Start
                        )
                        androidx.compose.material3.IconButton(modifier = Modifier
                            .size(50.dp),
                            colors = IconButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Blue,
                                disabledContentColor = Color.White,
                                disabledContainerColor = Color.Blue
                            ), onClick = {

                                val builder = StringBuilder()
                                builder.append("${formatFecha(it.update.toString())}\n\n")

                                if (it.sillaGrande.toInt() > 0) {
                                    val precio = precios["sillaGrande"] ?: 0.0
                                    val cantidad = it.sillaGrande.toInt()
                                    val subtotal = cantidad * precio
                                    builder.append("Silla Grande = ${it.sillaGrande} x $$precio = $$subtotal\n")
                                }
                                if (it.sillaChica.toInt() > 0) {
                                    val precio = precios["sillaChica"] ?: 0.0
                                    val subtotal = it.sillaChica.toInt() * precio
                                    builder.append("Silla Chica = ${it.sillaChica} x $$precio = $$subtotal\n")
                                }
                                if (it.sillaIndividual.toInt() > 0) {
                                    val precio = precios["sillaIndividual"] ?: 0.0
                                    val subtotal = it.sillaIndividual.toInt() * precio
                                    builder.append("Silla Individual = ${it.sillaIndividual} x $$precio = $$subtotal\n")
                                }
                                if (it.bancos.toInt() > 0) {
                                    val precio = precios["bancos"] ?: 0.0
                                    val subtotal = it.bancos.toInt() * precio
                                    builder.append("Banco = ${it.bancos} x $$precio = $$subtotal\n")
                                }
                                if (it.listonero.toInt() > 0) {
                                    val precio = precios["listonero"] ?: 0.0
                                    val subtotal = it.listonero.toInt() * precio
                                    builder.append("Listonero = ${it.listonero} x $$precio = $$subtotal\n")
                                }
                                if (it.listoneroCortados.toInt() > 0) {
                                    val precio = precios["listoneroCortado"] ?: 0.0
                                    val subtotal = it.listoneroCortados.toInt() * precio
                                    builder.append("Listonero cort. = ${it.listoneroCortados} x $$precio = $$subtotal\n")
                                }

                                if (it.portaGarrafon.toInt() > 0) {
                                    val precio = precios["PortaGarrafon"] ?: 0.0
                                    val subtotal = it.portaGarrafon.toInt() * precio
                                    builder.append("Porta Gar. = ${it.portaGarrafon} x $$precio = $$subtotal\n")
                                }



                                if (builder.isNotEmpty()) {
                                    builder.append("\nTotal: $${it.sueldo}")
                                }


                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, builder.toString())
                                    type = "text/plain"
                                }
                                val shareIntent = Intent.createChooser(sendIntent, null)
                                context.startActivity(shareIntent)
                            }) {
                            androidx.compose.material3.Icon(
                                Icons.Rounded.Share,
                                contentDescription = null
                            )
                        }
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.White),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 3.dp,
                                ),
                                textAlign = TextAlign.Start,
                                text = "M. Grande: " + it.sillaGrande,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                            )

                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                ),
                                textAlign = TextAlign.Start,
                                text = "M. Chica: " + it.sillaChica,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                            )

                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                ),
                                textAlign = TextAlign.Start,
                                text = "S. individual: " + it.sillaIndividual,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                            )

                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                ),
                                textAlign = TextAlign.Start,
                                text = "Bancos: " + it.bancos,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                            )

                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                ),
                                textAlign = TextAlign.Start,
                                text = "Porta Gr.: " + it.portaGarrafon,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                            )

                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.White),
                            verticalArrangement = Arrangement.Center
                        ) {
                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 3.dp,
                                ),
                                textAlign = TextAlign.Start,
                                text = "Papelera: " + it.papelera,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                            )

                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                ),
                                textAlign = TextAlign.Start,
                                text = "Listonero: " + it.listonero,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                            )

                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                ),
                                textAlign = TextAlign.Start,
                                text = "Listonero Cort." + it.listoneroCortados,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                            )

                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                ),
                                textAlign = TextAlign.Start,
                                text = "Botanero: " + it.botanero,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                            )


                        }

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp, start = 10.dp)
                            .background(Color.White)
                            .height(55.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Sueldo: $" + it.sueldo,
                            color = blue,
                            fontSize = 16.sp,
                            lineHeight = 23.sp,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle.Default,
                        )

                        Row(
                            Modifier,
                        ) {
                            androidx.compose.material3.IconButton(
                                modifier = Modifier
                                    .size(50.dp),
                                colors = IconButtonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.Red,
                                    disabledContentColor = Color.White,
                                    disabledContainerColor = Color.Red
                                ),
                                onClick = {
                                    //onEvent(Event.Delete(it.id))
                                    viewModel.deleteChair(it.id)
                                }) {
                                androidx.compose.material3.Icon(
                                    Icons.Rounded.Delete,
                                    contentDescription = null
                                )
                            }
                            androidx.compose.material3.IconButton(modifier = Modifier
                                .size(50.dp),
                                colors = IconButtonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = Color.Blue,
                                    disabledContentColor = Color.White,
                                    disabledContainerColor = Color.Red
                                ), onClick = {
                                    //onEvent(Event.Load(it.id))
                                }) {
                                androidx.compose.material3.Icon(
                                    Icons.Rounded.Edit,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp, top = 10.dp)
                            .background(Color.White),
                        verticalArrangement = Arrangement.Center
                    ) {
                        androidx.compose.material3.Text(
                            modifier = Modifier,
                            textAlign = TextAlign.Start,
                            text = "Nota: " + it.nota,
                            color = blue,
                            fontSize = 18.sp,
                            lineHeight = 23.sp,
                            style = TextStyle.Default,
                        )
                    }

                }
            }
        }

    }

}

fun formatFecha(fechaStr: String): String {
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
    val date: Date = inputFormat.parse(fechaStr)
    val diaSemana = SimpleDateFormat("EEEE", Locale("es", "ES")).format(date)
    val dia = SimpleDateFormat("dd", Locale("es", "ES")).format(date)
    val mes = SimpleDateFormat("MMMM", Locale("es", "ES")).format(date)
    val año = SimpleDateFormat("yyyy", Locale("es", "ES")).format(date)
    return "${diaSemana.replaceFirstChar { it.uppercase() }} $dia ${mes.replaceFirstChar { it.uppercase() }} $año"
}