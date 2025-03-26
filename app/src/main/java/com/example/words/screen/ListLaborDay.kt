package com.example.words.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
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
import com.example.words.Model.Event
import com.example.words.Model.LaborDayViewModel
import com.example.words.ui.theme.blue


@Composable
fun ListLaborDay(navController: NavController, viewModel: LaborDayViewModel = hiltViewModel()){

    val listLaborDay by viewModel.all.observeAsState()
    val scrollState = rememberScrollState()
    var ishours = 0
    listLaborDay?.forEach { action ->
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
                            Row (modifier = Modifier.padding(bottom = 15.dp)) {
                                androidx.compose.material.Text(
                                    "Horas : ",
                                    color = blue,
                                    fontSize = 24.sp
                                )
                                androidx.compose.material.Text(
                                    text = "${ishours}", color = blue, fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }

                            Row {
                                androidx.compose.material.Text(
                                    "Pago : $",
                                    color = blue,
                                    fontSize = 24.sp
                                )
                                androidx.compose.material.Text(
                                    text = "${ishours * 28}" , color = blue, fontSize = 24.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }

                        }

                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 15.dp, top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                        }
                    }
                }

                item() {
                    listLaborDay?.forEach { it ->
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
                                                //onEvent(Event.Delete(it.id))
                                                viewModel.deleteItemLaborDay(it.id)
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
                                                //onEvent(Event.Load(it.id))
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


    }
}