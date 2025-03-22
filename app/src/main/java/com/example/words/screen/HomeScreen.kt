package com.example.words.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.words.R
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.progressBlue
import java.util.Collections.copy
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.example.words.Model.Event
import com.example.words.navigation.Screen
import com.example.words.ui.theme.tickColor
import com.example.words.ui.theme.white


@Composable
fun HomeScreen(navController: NavController) {
    HomeGridScreen(navController = navController)
}


@Composable
fun HomeGridScreen(navController: NavController) {
    val foodItems = listOf(
        FoodItem("Dia de trabajo", R.drawable.ic_launcher_background),
        FoodItem("Soldadura de sillas", R.drawable.soldador),
        FoodItem("Pago de tejido", R.drawable.money),
        FoodItem("Pago de pintura", R.drawable.cash_delivery),
        FoodItem("Lista sillas armados", R.drawable.check_list),
        FoodItem("Lista tejido", R.drawable.completed_task),
        FoodItem("Lista pintura", R.drawable.list),
        FoodItem("Medidas de silla", R.drawable.measure_tape),
    )


    LazyVerticalGrid(
        contentPadding = PaddingValues(20.dp),
        columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        itemsIndexed(foodItems) { index, item ->
            FoodItemCard(foodItems[index], onEvent = { items ->
                Log.d("Print Log ========>", " Screeen:: Resul: ${items}")
                when(items){
                    "Dia de trabajo"->{
                        navController.navigate(Screen.WeeksScreen.route)
                    }
                    "Soldadura de sillas"->{
                        navController.navigate(Screen.WorkScreen.route)
                    }
                    "Pago de tejido"->{
                    }
                    "Pago de pintura"->{
                        navController.navigate(Screen.PaintScreen.route)
                    }
                    "Lista sillas armados"->{
                        navController.navigate(Screen.ListChairs.route)
                    }
                    "Lista pintura"->{
                        navController.navigate(Screen.ListPaint.route)
                    }
                    "Lista tejido"->{
                        navController.navigate(Screen.WorkScreen.route)
                    }
                    "Medidas de silla"->{
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
fun FoodItemCard(item: FoodItem, onEvent: (String) -> Unit ) {
    Box(
        modifier = Modifier
            .padding(1.dp) // Ajusta el padding según lo necesites
    ) {
        // Imagen redonda
        RoundImageWithBorder(imagePainter = painterResource(id = item.imageRes))
        // Card con las esquinas redondeadas solo del lado izquierdo
        Card(
            modifier = Modifier
                .padding(start = 55.dp, end = 20.dp) // Ajusta para que el card quede al lado de la imagen
                .height(50.dp) // Altura del card
                .fillMaxWidth().clickable {
                    onEvent(item.name)
                }
                .clip(
                    RoundedCornerShape(bottomEnd = 20.dp) // Solo redondear el lado izquierdo
                ),
            elevation = 8.dp
        ) {
            // Contenido dentro del Card
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(blue)
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    textAlign = TextAlign.Center, text = item.name, fontSize = 12.sp, color = white,
                    fontWeight = FontWeight.Medium,
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
            modifier = Modifier.fillMaxSize().padding(4.dp) // Hace que la imagen llene todo el espacio
        )
    }
}

// Modelo de datos
data class FoodItem(val name: String, val imageRes: Int)