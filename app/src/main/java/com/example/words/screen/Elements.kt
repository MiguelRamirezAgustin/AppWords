package com.example.words.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.words.R
import com.example.words.ui.theme.isColorBlue


@Composable
fun ShowToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    val context = LocalContext.current

    LaunchedEffect(message) {
        Toast.makeText(context, message, duration).show()
    }
}


@Composable
fun IsBtn(isText:String, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(contentColor = isColorBlue, containerColor = isColorBlue),
        modifier = Modifier
            .height(56.dp), onClick = {
            onClick.invoke()
        }) {
        androidx.compose.material3.Text(
            "${isText}", fontSize = 20.sp,
            color = Color.White
        )
    }
}