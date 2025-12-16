package com.example.words.screen.component

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.example.words.Model.Event
import com.example.words.ui.theme.LightBrown
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.tickColor

@Composable
fun AlerDialogPopupAdd(openDialog: MutableState<Boolean>, onEvent: () -> Unit) {

    if (openDialog.value) {
        Popup(
            onDismissRequest = {
                openDialog.value = false
            },
            properties = PopupProperties(focusable = true),
            alignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(onClick = { openDialog.value = false })
                    .padding(start = 16.dp, end = 16.dp, bottom = 38.dp)

            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .align(Alignment.Center)
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
                                text = "Eliminar lista de precios",
                                fontSize = 20.sp,
                                color = tickColor,
                                modifier = Modifier
                                    .padding(top = 5.dp, bottom = 20.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                        Spacer(modifier = Modifier.padding(bottom = 16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    bottom = 24.dp,
                                    start = 24.dp,
                                    end = 24.dp,
                                    top = 10.dp
                                ), horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            BtnCustoms(
                                text = "Confirmar",
                                onClick = {
                                    onEvent.invoke()
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
                                    openDialog.value = false
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

