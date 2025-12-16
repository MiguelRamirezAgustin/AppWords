package com.example.words.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.words.Model.ChairsMovementsViewModel
import com.example.words.Model.Event
import com.example.words.db.model.ChairMovements
import com.example.words.ui.theme.blue
import java.sql.Date
import java.text.DateFormat


@Composable
fun ChairHistory(
    navController: NavController,
    viewModel: ChairsMovementsViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista de sillas grandes",
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
            MaincontentListHistory(viewModel, paddingValues)

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaincontentListHistory(
    viewModel: ChairsMovementsViewModel,
    paddingValues: PaddingValues,
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }
    val movimientos = viewModel.movimientos.collectAsState()
    val totalStock by viewModel.totalStock.collectAsState()
    var entrada = remember { mutableStateOf("") }
    var salida = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(paddingValues)) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Sillas grandes actual: $totalStock", fontSize = 30.sp, color = blue)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Absolute.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                IsBtn(isText = "Agregar productos", onClick = {
                    salida.value = ""
                    entrada.value = ""
                    showSheet = true
                })

            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {

                item {
                    movimientos.value.forEach {
                        it
                        CardItems(
                            it,
                            onDelete = { mov ->
                                viewModel.deleteIdMovements(it.id)
                            }
                        )
                    }
                }


            }
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
                    .padding(bottom = 50.dp, end = 30.dp, start = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Nuevos productos",
                    fontSize = 20.sp,
                    color = blue,
                    modifier = Modifier.padding(vertical = 15.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = entrada.value,
                        onValueChange = { nuevo ->
                            if (nuevo.isEmpty() || nuevo.matches(Regex("^[0-9]+$"))) {
                                entrada.value = nuevo
                            }
                        },
                        label = { Text("Entrada") },
                        placeholder = { Text("PZ 00", fontSize = 25.sp) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .width(150.dp)
                            .height(65.dp)
                    )
                    Spacer(Modifier.height(24.dp))
                    IsBtn(isText = "Aceptar", onClick = {
                        showSheet = false
                        viewModel.agregarEntrada(entrada.value.toInt())
                    })
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedTextField(
                        value = salida.value,
                        onValueChange = { nuevo ->
                            if (nuevo.isEmpty() || nuevo.matches(Regex("^[0-9]+$"))) {
                                salida.value = nuevo
                            }
                        },
                        label = { Text("Salida") },
                        placeholder = {
                            Text(
                                "PZ 00",
                                fontSize = 25.sp
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .width(150.dp)
                            .height(65.dp)
                    )
                    Spacer(Modifier.height(24.dp))
                    IsBtn(isText = "Aceptar", onClick = {
                        showSheet = false
                        viewModel.registrarVenta(salida.value.toInt())
                    })
                }
            }
        }
    }
}


@Composable
fun CardItems(chairMovements: ChairMovements,
              onDelete: (ChairMovements) -> Unit) {

    var showDialog by remember { mutableStateOf(false) }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Seguro que quieres eliminar este movimiento?") },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(chairMovements)
                    showDialog = false
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .padding(bottom = 15.dp, end = 10.dp, start = 10.dp)
            .fillMaxWidth()
            .clickable { showDialog = true}
            .border(
                1.dp,
                if (chairMovements.tipo.uppercase() == "ENTRADA") Color.Blue else Color.Red,
                RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val fecha = DateFormat.getDateTimeInstance().format(Date(chairMovements.fecha))
            Row(modifier = Modifier.weight(1f)) {

                Text(
                    text = "${chairMovements.tipo.uppercase()} - PZA: ${chairMovements.cantidad} ",
                    fontSize = 19.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Row(modifier = Modifier.weight(1f)) {

                Text(
                    text = "$fecha", fontSize = 15.sp, color = Color.Black,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}
