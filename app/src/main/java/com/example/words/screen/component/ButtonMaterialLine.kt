package com.example.words.screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.words.Model.Event


@Composable
fun ButtonMaterialLine(title:String, eventBtn :() -> Unit){
    OutlinedButton(
        modifier = Modifier
            .padding(horizontal = 56.dp, vertical = 20.dp)
            .fillMaxWidth()
            .height(56.dp),
        onClick = {eventBtn.invoke() }) {
        Text(text = title)
    }
}