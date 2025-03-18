package com.example.words.screen

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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.words.Model.ChairsViewModel
import com.example.words.ui.theme.blue

@Composable
fun ListChairs(navController: NavController, viewModel: ChairsViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista",
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
    val chairs by viewModel.all.observeAsState()  // Escuchar cambios en la BD
    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            text = "Lista de Sillas",
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 10.dp)
        )
        chairs?.forEach { it ->
            Card(
                modifier = Modifier
                    .background(Color.White)
                    .padding(end = 15.dp, start = 15.dp, top = 15.dp, bottom = 5.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {}
                    .height(100.dp),
                contentColor = Color.White,
                shape = RoundedCornerShape(12.dp),
                elevation = 9.dp,
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
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 3.dp),
                                text = "Sueldo: $ " + it.sueldo,
                                color = blue,
                                fontSize = 16.sp,
                                lineHeight = 23.sp,
                                style = TextStyle.Default,
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
                    }


                }
            }
        }

    }

}