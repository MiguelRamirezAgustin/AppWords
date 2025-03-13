package com.example.words.screen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@Composable
fun BtnCustoms(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = buttonColors(),
    style: TextStyle,
    elevation: ButtonElevation,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = colors,
        shape = RoundedCornerShape(8.dp),
        elevation = elevation,
        enabled = enabled,
    ) {
        Text(
            text = text,
            style =  style
        )
    }
}