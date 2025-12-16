package com.example.words.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
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
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.words.Model.MaterialPriceViewModel
import com.example.words.db.model.MaterialPrice
import com.example.words.screen.component.AlerDialogPopupAdd
import com.example.words.screen.component.CustomTopAppBar

@Composable
fun ChairQuote(navController: NavController, viewModel: MaterialPriceViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Cotizacioness",
                onBackClick = { navController.popBackStack() }
            )
        },
        floatingActionButton = {},
        content = { paddingValues ->
            MaincontentListQoute(paddingValues, viewModel)
        }
    )
}

@Composable
fun MaincontentListQoute(paddingValues: PaddingValues, viewModel: MaterialPriceViewModel) {

    val materialPrice by viewModel.all.observeAsState()
    val allInputs = remember { mutableStateMapOf<String, Double>() }
    val allInputsExtra = remember { mutableStateMapOf<String, Double>() }
    val cantidadTotal = allInputs.values.sum()
    val totalGeneral = allInputsExtra.values.sum()
    val showDialog = remember { mutableStateOf(false) }
    val idDelete = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Total: $ ${"%.2f".format(cantidadTotal+totalGeneral)}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp),
            fontSize = 18.sp,
        )

        materialPrice?.forEach {
            it
            MaterialPriceList(
                materialPrice = it,
                allInputs = allInputs,
                onEvent = {
                    idDelete.value = it.id?.toInt() ?: 0
                    showDialog.value = true
                }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            PriceRow(
                allInputs = allInputsExtra,
                title = "Pintura",
                modifier = Modifier
            )
            Spacer(Modifier.width(15.dp))
            PriceRow(
                allInputs = allInputsExtra,
                title = "Hilo",
                modifier = Modifier
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            PriceRow(
                allInputs = allInputsExtra,
                title = "Tejido",
                modifier = Modifier
            )
            Spacer(Modifier.width(15.dp))

            PriceRow(
                allInputs = allInputsExtra,
                title = "Tapon",
                modifier = Modifier
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            PriceRow(
                allInputs = allInputsExtra,
                title = "Armado",
                modifier = Modifier
            )
            Spacer(Modifier.width(15.dp))
            PriceRow(
                allInputs = allInputsExtra,
                title = "Cortado",
                modifier = Modifier
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            PriceRow(
                allInputs = allInputsExtra,
                title = "Viaje",
                modifier = Modifier
            )
            Spacer(Modifier.width(15.dp))
            PriceRow(
                allInputs = allInputsExtra,
                title = "Extras",
                modifier = Modifier
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            PriceRow(
                allInputs = allInputsExtra,
                title = "Soldadura",
                modifier = Modifier
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            PriceRow(
                allInputs = allInputsExtra,
                title = "Alambron",
                modifier = Modifier
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            PriceRow(
                allInputs = allInputsExtra,
                title = "Disco de corte",
                modifier = Modifier
            )
        }

    }
    AlerDialogPopupAdd(
        openDialog = showDialog,
        onEvent = {
            viewModel.deleteMaterialPrice(idDelete.value)
            showDialog.value = false
          Log.d("Print Log ========>", " Screeen::${idDelete.value}")
        }
    )
}


@Composable
fun MaterialPriceList(materialPrice: MaterialPrice, allInputs: MutableMap<String, Double>,onEvent: () -> Unit) {

    val fields = listOf(
        "tb_1_c18" to materialPrice.tb_1_c18,
        "tb_1_c20" to materialPrice.tb_1_c20,
        "tb_78_c18" to materialPrice.tb_78_c18,
        "tb_78_c20" to materialPrice.tb_78_c20,
        "tb_34_c18" to materialPrice.tb_34_c18,
        "tb_34_c20" to materialPrice.tb_34_c20,
        "tb_12_c18" to materialPrice.tb_12_c18
    )

    // Guardamos los valores de cada input
    val metrosInputs = remember {
        fields.associate { (name, _) -> name to mutableStateOf("") }.toMutableMap()
    }

    Column(
        modifier = Modifier
            .padding(end = 16.dp, start = 16.dp, bottom = 20.dp, top = 20.dp)
            .height(300.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        fields.forEach { (name, value) ->
            val precioPorMetro = value / 6.0
            val metrosText = metrosInputs[name]?.value ?: ""
            val metros = metrosText.toDoubleOrNull() ?: 0.0
            val total = metros * precioPorMetro

            Row(
                modifier = Modifier.fillMaxWidth().clickable {
                    onEvent()
                },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Nombre y precio original
                Text(
                    text = "$name\n$${value}",
                    modifier = Modifier.weight(1f),
                    fontStyle = FontStyle.Italic,
                    fontSize = 16.sp,
                )

                // Precio por metro
                Text(
                    text = "Mts $${"%.2f".format(precioPorMetro)}",
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 5.dp),
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,

                    )

                // Input de metros
                OutlinedTextField(
                    value = metrosText,
                    onValueChange = { input ->
                        if (input.all { it.isDigit() }) {
                            metrosInputs[name]?.value = input
                            val metros = input.toDoubleOrNull() ?: 0.0
                            allInputs[name] = metros * precioPorMetro
                        }
                    },
                    placeholder = { Text("0") },
                    modifier = Modifier.width(80.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Text(
                    text = "$ ${"%.2f".format(total)}",
                    fontStyle = FontStyle.Normal,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 15.dp)
                )
            }
        }
    }
}


@Composable
fun PriceRow(
    allInputs: MutableMap<String, Double>,
    title: String,
    modifier: Modifier = Modifier
) {
    var inputValue = remember { mutableStateOf("") }
    val cantidad = inputValue.value.toDoubleOrNull() ?: 0.0

    Row(
        modifier = modifier.height(70.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            modifier = Modifier,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp
        )

        OutlinedTextField(
            value = inputValue.value,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    inputValue.value = input
                    allInputs[title] = input.toDoubleOrNull() ?: 0.0
                }
            },
            placeholder = { Text("0") },
            modifier = Modifier
                .padding(end = 15.dp, start = 15.dp)
                .width(60.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Text(
            text = "$${"%.2f".format(cantidad)}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier,
            fontSize = 16.sp
        )
    }
}
