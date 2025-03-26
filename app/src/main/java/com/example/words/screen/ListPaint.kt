package com.example.words.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.words.Model.ChairsViewModel
import com.example.words.Model.PaintViewModel
import com.example.words.ui.theme.LightBrown
import com.example.words.ui.theme.blue

@Composable
fun ListPaint(navController: NavController, viewModel: PaintViewModel = hiltViewModel()) {
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
            maincontentListPaint(viewModel, paddingValues)

        }
    )
}

@Composable
fun maincontentListPaint(viewModel: PaintViewModel, paddingValues: PaddingValues) {
    val paintChair by viewModel.all.observeAsState()  // Escuchar cambios en la BD
    val scrollState = rememberScrollState()
    Log.d("Print Log ========>", " chair::Resul: ${paintChair}")
    Column(modifier = Modifier.padding(bottom = 30.dp, top = 10.dp)
        .verticalScroll(scrollState),) {
        paintChair?.forEach { it ->
            Card(
                modifier = Modifier
                    .background(Color.White)
                    .padding(end = 15.dp, start = 15.dp, top = 15.dp, bottom = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {}
                    .height(170.dp),
                contentColor = Color.White,
                shape = RoundedCornerShape(12.dp),
                elevation = 9.dp,
                border = BorderStroke(1.dp, blue),
                backgroundColor = Color.White
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 10.dp, end = 10.dp)
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
                                text = "Botanero: " + it.botanero,
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
                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 3.dp,
                                ),
                                textAlign = TextAlign.Start,
                                text = "Arañas: " + it.arana,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                            )
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                text = "Total: $ " + it.total,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
                                fontWeight = FontWeight.Bold
                            )

                            androidx.compose.material3.Text(
                                modifier = Modifier.padding(
                                    top = 8.dp,
                                ),
                                text = FormatearFechaDay(it.update.toString()).toString(),
                                color = blue,
                                fontSize = 15.sp,
                                lineHeight = 24.sp,
                                style = TextStyle.Default,
                            )


                        }
                    }

                    Row(
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 10.dp,
                            end = 10.dp,
                            bottom = 10.dp
                        )
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 16.sp,
                                        color = blue,
                                    )
                                ) {
                                    append(
                                       "Nota: "
                                    )
                                }
                                append(" ")
                                pushStringAnnotation(
                                    tag = "",
                                    annotation = ""
                                )
                                withStyle(
                                    style = SpanStyle(
                                        color = blue,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                    )
                                ) {
                                    append( it.nota)
                                }
                            },
                            fontFamily = FontFamily.Default,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            lineHeight = 23.sp,
                            color = LightBrown,
                            modifier = Modifier.weight(3f),
                            textAlign = TextAlign.Start,
                        )
                        Row(
                            Modifier.weight(1f)
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
                                    viewModel.deletePaint(it.id)
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

                }
            }
        }

    }

}