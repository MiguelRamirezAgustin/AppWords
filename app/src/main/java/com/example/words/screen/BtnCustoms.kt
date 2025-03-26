package com.example.words.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.words.ui.theme.blue
import com.example.words.ui.theme.tickColor


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
            style = style
        )
    }
}

@Composable
fun BtnCornerRow(
    title: String,
    onClick: () -> Unit,
    style: TextStyle,
    modifier: Modifier = Modifier,
    elevation: ButtonElevation,
    colorBorder:Color
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(1.dp, colorBorder),
        shape = RoundedCornerShape(50), // = 50% percent
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red),
        elevation = elevation,
    ) {
        Text(
            text = title,
            style = style
        )
    }
}