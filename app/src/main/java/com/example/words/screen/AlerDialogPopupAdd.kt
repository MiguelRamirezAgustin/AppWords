package com.example.words.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.words.Model.Event
import com.example.words.ui.theme.GreyLight
import com.example.words.ui.theme.LightBrown
import com.example.words.ui.theme.LightGrey
import com.example.words.ui.theme.TextFieldBorder
import com.example.words.ui.theme.light_tangerine
import com.example.words.ui.theme.tickColor
import com.example.words.ui.theme.white

@Composable
fun AlerDialogPopupAdd(openDialog: Boolean, onEvent: (Event) -> Unit) {
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    val keyboardController = LocalSoftwareKeyboardController.current
    val isFocused = remember { mutableStateOf(false) }

    var texts by remember { mutableStateOf("") }

    if (openDialog) {
        Popup(
            onDismissRequest = {
                openDialog == false
            },
            properties = PopupProperties(focusable = true),
            alignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(onClick = { openDialog == false })
                    .padding(start = 16.dp, end = 16.dp, bottom = 28.dp)

            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .align(Alignment.BottomCenter)
                        .background(Color.White)

                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                    ) {
                        Column(
                            Modifier
                                .padding(start = 24.dp, top = 29.dp, end = 24.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "Agregar nuevo dia",
                                fontSize = 30.sp,
                                color = tickColor,
                                modifier = Modifier.padding(top = 5.dp, bottom = 20.dp)
                            )
                            OutlinedTextField(
                                value = texts,
                                onValueChange = {
                                    // Filtrar solo los caracteres numéricos y limitar la longitud
                                    val filteredText = it.filter { char -> char.isDigit() }.take(2)
                                    texts = filteredText
                                    Log.d("Print Log ========>", " Screeen::${it} ")
                                    if (!it.isEmpty()){
                                        onEvent(Event.SetText(it))
                                    }

                                },
                                label = { Text("Ingrese solo números") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )




                        }
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        Row ( modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 24.dp,
                                start = 24.dp,
                                end = 24.dp,
                                top = 10.dp
                            ), horizontalArrangement = Arrangement.SpaceBetween) {

                            BtnCustoms(
                                text = "Confirmar",
                                onClick = {
                                    if (!texts.isEmpty()){
                                        onEvent(Event.Save)
                                    }

                                },
                                colors = ButtonDefaults.buttonColors(
                                    disabledContainerColor = Color.White,
                                    contentColor = LightBrown,
                                    containerColor = LightBrown

                                ),
                                modifier = Modifier
                                    .height(56.dp),
                                elevation = ButtonDefaults.elevatedButtonElevation(
                                    defaultElevation = 0.dp
                                ),
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                            BtnCustoms(
                                text = "Cancelar",
                                onClick = {
                                    onEvent(Event.CloseDialog)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    disabledContainerColor = Color.White,
                                    contentColor = tickColor,
                                    containerColor = tickColor
                                ),
                                modifier = Modifier
                                    .height(56.dp),
                                elevation = ButtonDefaults.elevatedButtonElevation(
                                    defaultElevation = 0.dp
                                ),
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

