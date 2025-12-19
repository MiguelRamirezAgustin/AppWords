package com.example.words.screen

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import com.example.words.R
import com.example.words.ui.theme.LightBrown
import com.example.words.ui.theme.white
import java.io.File
import java.io.FileOutputStream
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NewNoteScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var name = remember { mutableStateOf("") }
    var quantity = remember { mutableStateOf("") }
    var price = remember { mutableStateOf("") }
    var nombreCliente = remember { mutableStateOf("") }
    val showClientField = remember { mutableStateOf(false) }
    val showClientError = remember { mutableStateOf(false) }
    // Lista de productos
    val items = remember { mutableStateListOf<ProductItem>() }

    // Total general
    val totalGeneral = items.sumOf { it.total }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = Color.White,
            topBar = {
            },
            bottomBar = {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(white)
                        .padding(end = 24.dp, start = 24.dp, top = 16.dp, bottom = 65.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Button(
                        modifier = Modifier
                            .height(56.dp)
                            .padding(horizontal = 60.dp)
                            .fillMaxWidth(),
                        colors = ButtonColors(
                            containerColor = LightBrown,
                            disabledContentColor = LightBrown,
                            contentColor = LightBrown,
                            disabledContainerColor = LightBrown
                        ),
                        onClick = {
                            if (items.isEmpty()) return@Button

                            if (nombreCliente.value.isBlank()) {
                                showClientField.value = true
                                showClientError.value = true
                                return@Button
                            }

                            val pdfFile = generatePdf(
                                context = context,
                                clientName = nombreCliente.value,
                                items = items,
                                totalGeneral = totalGeneral
                            )
                            sharePdf(context, pdfFile)
                        }
                    ) {
                        Text(
                            "Crear Nota",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }

        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp, top = 20.dp)
                    .padding(innerPadding)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                BodyNewNoteScreen(name, quantity, price, items, totalGeneral, nombreCliente,showClientField,
                    showClientError)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyNewNoteScreen(
    name: MutableState<String>,
    quantity: MutableState<String>,
    price: MutableState<String>,
    items: SnapshotStateList<ProductItem>,
    totalGeneral: Double,
    nombreCliente: MutableState<String>,
    showClientField: MutableState<Boolean>,
    showClientError: MutableState<Boolean>
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, end = 10.dp, start = 10.dp)
    ) {
        if (showClientField.value) {
            OutlinedTextField(
                value = nombreCliente.value,
                onValueChange = {
                    nombreCliente.value = capitalizeFirst(it)
                    showClientError.value = false
                },
                label = { Text("Nombre del cliente") },
                isError = showClientError.value,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = if (showClientError.value) Color.Red else Color.Blue,
                    unfocusedBorderColor = if (showClientError.value) Color.Red else Color.Gray
                )
            )

            if (showClientError.value) {
                Text(
                    text = "El nombre del cliente es obligatorio",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }


        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = {
                name.value = capitalizeFirst(it)
            },
            label = { Text("Producto", fontSize = 19.sp, color = Color.Black) },
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 20.sp
            ),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Blue
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = quantity.value,
                onValueChange = { quantity.value = it },
                label = { Text("Piezas", fontSize = 19.sp, color = Color.Black) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 20.sp
                ),
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Blue
                )
            )

            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                value = price.value,
                onValueChange = { price.value = it },
                label = { Text("Precio", fontSize = 24.sp, color = Color.Black) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontSize = 20.sp
                ),
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Blue,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.Blue
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (name.value.isNotBlank() && quantity.value.isNotBlank() && price.value.isNotBlank()) {
                        items.add(
                            ProductItem(
                                name = name.value,
                                quantity = quantity.value.toInt(),
                                clientName = nombreCliente.value,
                                price = price.value.toDouble()
                            )
                        )
                        name.value = ""
                        quantity.value = ""
                        price.value = ""
                        nombreCliente.value = ""
                    }
                },
                elevation = ButtonDefaults.buttonElevation(0.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightBrown,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Agregar"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Agregar", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }


        Spacer(modifier = Modifier.height(24.dp))


        items.forEach {
            it
            ProductItemRow(
                item = it,
                onDelete = {
                    items.removeAll { it.id == it.id }
                })
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))


        Text(
            text = "Total: $${formatMoney(totalGeneral)}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.End)
        )
    }
}


@Composable
fun ProductItemRow(
    item: ProductItem,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 18.sp
            )

            Text(
                text = "Eliminar",
                color = Color.Red,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { onDelete() }
            )
            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Piezas: ${item.quantity}")
                Text("Precio: $${item.price}")
                Text(
                    text = "$${formatMoney(item.total)}",
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}


data class ProductItem(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val quantity: Int,   // piezas
    val clientName: String,
    val price: Double    // precio unitario
) {
    val total: Double
        get() = quantity * price
}


fun sharePdf(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.provider",
        file
    )

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "application/pdf"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(
        Intent.createChooser(intent, "Compartir PDF")
    )
}


fun generatePdf(
    context: Context,
    clientName: String,
    items: List<ProductItem>,
    totalGeneral: Double
): File {

    val pdfDocument = PdfDocument()
    val paint = Paint()

    val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
    val page = pdfDocument.startPage(pageInfo)
    val canvas = page.canvas

    // ───────── HEADER ─────────
    var y = 40

    // LOGO
    val logoBitmap = loadBitmapFromDrawable(
        context = context,
        resId = R.drawable.logo,
        width = 95,
        height = 95
    )


    canvas.drawBitmap(
        logoBitmap,
        (pageInfo.pageWidth - logoBitmap.width - 40).toFloat(),
        y.toFloat(),
        null
    )

    // TEXTO IZQUIERDA
    paint.textSize = 22f
    paint.isFakeBoldText = false

    canvas.drawText(
        "Cliente: ${capitalizeFirst(clientName)}",
        40f,
        (y + 20).toFloat(),
        paint
    )
    y += 15
    canvas.drawText(
        "Fecha: ${getCurrentDate()}",
        40f,
        (y + 42).toFloat(),
        paint
    )

    y += maxOf(logoBitmap.height, 60) + 30

    // ───────── TÍTULO ─────────
    paint.textSize = 26f
    paint.isFakeBoldText = true
    canvas.drawText("Nota de remisión", 40f, y.toFloat(), paint)

    y += 30

    // ───────── ENCABEZADOS ─────────
    paint.textSize = 19f
    canvas.drawText("Producto", 40f, y.toFloat(), paint)
    canvas.drawText("Piezas", 240f, y.toFloat(), paint)
    canvas.drawText("Precio", 340f, y.toFloat(), paint)
    canvas.drawText("Total", 450f, y.toFloat(), paint)

    paint.strokeWidth = 1f
    canvas.drawLine(40f, (y + 6).toFloat(), 555f, (y + 6).toFloat(), paint)

    y += 35
    paint.isFakeBoldText = false

    // ───────── LISTADO ─────────
    items.forEach { item ->
        canvas.drawText(capitalizeFirst(item.name), 40f, y.toFloat(), paint)
        canvas.drawText(item.quantity.toString(), 240f, y.toFloat(), paint)
        canvas.drawText("$ ${formatMoney(item.price)}", 340f, y.toFloat(), paint)
        canvas.drawText("$ ${formatMoney(item.total)}", 450f, y.toFloat(), paint)
        y += 30
    }

    // ───────── TOTAL ─────────
    y += 20
    paint.isFakeBoldText = true
    paint.textSize = 20f
    canvas.drawText(
        "Total: $ ${formatMoney(totalGeneral)}",
        390f,
        y.toFloat(),
        paint
    )

    pdfDocument.finishPage(page)

    // ARCHIVO FINAL
    val file = File(context.cacheDir, "nota_${System.currentTimeMillis()}.pdf")
    pdfDocument.writeTo(FileOutputStream(file))
    pdfDocument.close()

    return file
}


fun getCurrentDate(): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date())
}

fun formatMoney(value: Double): String {
    val symbols = DecimalFormatSymbols(Locale.US)
    val df = DecimalFormat("#,##0.00", symbols)
    return df.format(value)
}

fun capitalizeFirst(text: String): String {
    return text
        .trimStart() // no corta lo que el usuario sigue escribiendo
        .replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault())
            else it.toString()
        }
}

fun loadBitmapFromDrawable(
    context: Context,
    @DrawableRes resId: Int,
    width: Int,
    height: Int
): Bitmap {
    val bitmap = BitmapFactory.decodeResource(context.resources, resId)
    return Bitmap.createScaledBitmap(bitmap, width, height, true)
}
