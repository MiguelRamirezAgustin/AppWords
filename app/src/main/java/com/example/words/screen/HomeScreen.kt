package com.example.words.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.words.R
import com.example.words.ui.theme.blue
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavController
import com.example.words.navigation.Screen


@Composable
fun HomeScreen(navController: NavController) {
    HomeGridScreen(navController = navController)
}


@Composable
fun HomeGridScreen(navController: NavController) {
    val foodItems = listOf(
        FoodItem("Dia de trabajo", R.drawable.horas_trabjajo),
        FoodItem("Soldadura de sillas", R.drawable.welding),
        FoodItem("Pago de tejido", R.drawable.money),
        FoodItem("Pago de pintura", R.drawable.cash_delivery),
        FoodItem("Lista sillas armados", R.drawable.list_chair),
        FoodItem("Lista tejido", R.drawable.completed_task),
        FoodItem("Lista pintura", R.drawable.spray_gun),
        FoodItem("Cotizaciones", R.drawable.measure_tape),
        FoodItem("Historial", R.drawable.list_chair),
        FoodItem("Precios", R.drawable.list),
    )


    LazyVerticalGrid(
        contentPadding = PaddingValues(10.dp),
        columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Imagen redonda",
                    modifier = Modifier
                        .size(90.dp) // Hace que la imagen llene todo el espacio
                )
            }
        }
        itemsIndexed(foodItems) { index, item ->
            FoodItemCard(foodItems[index], onEvent = { items ->
                Log.d("Print Log ========>", " Screeen:: Resul: ${items}")
                when (items) {
                    "Dia de trabajo" -> {
                        navController.navigate(Screen.LaborDayScreen.route)
                    }

                    "Soldadura de sillas" -> {
                        navController.navigate(Screen.WorkScreen.route)
                    }

                    "Pago de tejido" -> {
                        navController.navigate(Screen.ChairsTejidoScreen.route)
                    }

                    "Pago de pintura" -> {
                        navController.navigate(Screen.PaintScreen.route)
                    }

                    "Lista sillas armados" -> {
                        navController.navigate(Screen.ListChairs.route)
                    }

                    "Lista pintura" -> {
                        navController.navigate(Screen.ListPaint.route)
                    }
                    "Historial" -> {
                        navController.navigate(Screen.ChairHistory.route)
                    }
                    "Cotizaciones" -> {
                        navController.navigate(Screen.ChairQuote.route)
                    }
                    "Lista tejido" -> {
                        navController.navigate(Screen.ListChairsTedijo.route)
                    }

                    "Lista horas de trabajo" -> {
                        navController.navigate(Screen.ListLaborDay.route)
                    }

                    "Precios"-> {
                        navController.navigate(Screen.MaterialPriceScreen.route)
                    }

                }
            })

            if ((index + 1) % 4 == 0) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) // Espacio o salto de línea visual
            }
        }
    }

}

@Composable
fun FoodItemCard(item: FoodItem, onEvent: (String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(
                start = 26.dp,
                end = 26.dp,
                bottom = 5.dp
            ) // Ajusta el padding según lo necesites
    ) {
        // Imagen redonda
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onEvent(item.name) }
                .size(50.dp) // Tamaño de la imagen
                .clip(CircleShape) // Hace la imagen redonda
                .border(0.5.dp, Color.Black, CircleShape) // Borde negro de 2 dp
        ) {
            Row( // Centra horizontalmente los elementos
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Imagen redonda
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(0.5.dp, Color.Black, CircleShape),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    RoundImageWithBorder(imagePainter = painterResource(id = item.imageRes))
                }

                // Texto centrado
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    color = blue,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 20.dp) // Espacio opcional entre imagen y texto
                )
            }
        }
    }
}


@Composable
fun RoundImageWithBorder(imagePainter: Painter) {
    Box(
        modifier = Modifier
            .size(50.dp) // Tamaño de la imagen
            .clip(CircleShape) // Hace la imagen redonda
            .border(0.5.dp, Color.Black, CircleShape) // Borde negro de 2 dp
    ) {
        Image(
            painter = imagePainter,
            contentDescription = "Imagen redonda",
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp) // Hace que la imagen llene todo el espacio
        )
    }
}

// Modelo de datos
data class FoodItem(val name: String, val imageRes: Int)