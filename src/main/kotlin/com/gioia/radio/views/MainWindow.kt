package com.gioia.radio.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun MainWindow() {
    Row(modifier = Modifier.fillMaxHeight().padding(16.dp)) {
        Column(
            Modifier.weight(1f)
        ){
            StationsViewModel()
        }
        Column(
            Modifier.weight(1f).background(Yellow)
        ) {
            Text(text = "Weight = 2")
        }
    }
}