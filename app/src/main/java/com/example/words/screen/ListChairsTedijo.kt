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
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
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
import com.example.words.Model.ChairsTejidoViewModel
import com.example.words.Model.ChairsViewModel
import com.example.words.db.model.ChairsTejido
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.progressBlue

@Composable
fun ListChairsTedijo(navController: NavController, viewModel: ChairsTejidoViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de sillas tejidas",
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
fun maincontentList(viewModel: ChairsTejidoViewModel, paddingValues: PaddingValues) {
    val precios = mapOf(
        "sillaGrande" to 40,
        "sillaChica" to 30,
        "sillaIndividual" to 25,
        "bancos" to 50,
        "cuadrados" to 15,
        "cuadrado_mini" to 10
    )

    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val chairs by viewModel.all.observeAsState()  // Escuchar cambios en la BD
    Log.d("Print Log ========>", " ChairsTejidoViewModel::Resul: ${chairs}")
    Column(modifier = Modifier.padding(bottom = 30.dp, top = 10.dp)
        .verticalScroll(scrollState) ) {
        Spacer(modifier = Modifier.height(10.dp))
        chairs?.forEach { it ->
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
                Column(modifier = Modifier.fillMaxSize().padding(bottom = 10.dp)) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 5.dp, end = 10.dp)
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
                                text = "Cuadrados: " + it.cuadrados,
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
                                text = "Minis: " + it.cuadrado_mini,
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
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 3.dp),
                                text = "Sueldo: $ " + it.total,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                fontWeight = FontWeight.Bold,
                                style = TextStyle.Default,
                            )

                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                ),
                                text = "Dia: "+ FormatearFechaDay(it.update.toString()),
                                color = blue,
                                fontSize = 15.sp,
                                lineHeight = 24.sp,
                                style = TextStyle.Default,
                            )

                            Row(
                                Modifier,
                                verticalAlignment = Alignment.Bottom,
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
                                        disabledContainerColor = Color.Blue
                                    ), onClick = {

                                        val builder = StringBuilder()
                                        builder.append("Revisa si esta bien:\n\n")

                                        if (it.sillaGrande.toInt() > 0) {
                                            val precio = precios["sillaGrande"] ?: 0
                                            val subtotal = it.sillaGrande.toInt() * precio
                                            builder.append("Silla Grande = ${it.sillaGrande} x $$precio = $$subtotal\n")
                                        }
                                        if (it.sillaChica.toInt() > 0) {
                                            val precio = precios["sillaChica"] ?: 0
                                            val subtotal = it.sillaChica.toInt() * precio
                                            builder.append("Silla Chica = ${it.sillaChica} x $$precio = $$subtotal\n")
                                        }
                                        if (it.sillaIndividual.toInt() > 0) {
                                            val precio = precios["sillaIndividual"] ?: 0
                                            val subtotal = it.sillaIndividual.toInt() * precio
                                            builder.append("Silla Individual = ${it.sillaIndividual} x $$precio = $$subtotal\n")
                                        }
                                        if (it.bancos.toInt() > 0) {
                                            val precio = precios["bancos"] ?: 0
                                            val subtotal = it.bancos.toInt() * precio
                                            builder.append("Banco = ${it.bancos} x $$precio = $$subtotal\n")
                                        }
                                        if (it.cuadrados.toInt() > 0) {
                                            val precio = precios["cuadrados"] ?: 0
                                            val subtotal = it.cuadrados.toInt() * precio
                                            builder.append("Cuadrado = ${it.cuadrados} x $$precio = $$subtotal\n")
                                        }
                                        if (it.cuadrado_mini.toInt() > 0) {
                                            val precio = precios["cuadrado_mini"] ?: 0
                                            val subtotal = it.cuadrado_mini.toInt() * precio
                                            builder.append("Cuadrado Mini = ${it.cuadrado_mini} x $$precio = $$subtotal\n")
                                        }

                                        if (builder.isNotEmpty()) {
                                            builder.append("\nTotal: $${it.total}")
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
                        }
                    }
                    Column( modifier = Modifier
                        .fillMaxWidth().padding(start = 10.dp, end = 10.dp)
                        .background(Color.White),
                        verticalArrangement = Arrangement.Center) {
                       Text(
                            modifier = Modifier,
                            textAlign = TextAlign.Start,
                            text = "Nota: " + it.nota,
                            color = blue,
                            fontSize = 20.sp,
                            lineHeight = 23.sp,
                            style = TextStyle.Default,
                        )
                    }

                }
            }
        }

    }

}
