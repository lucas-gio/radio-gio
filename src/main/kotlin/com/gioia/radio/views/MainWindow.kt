package com.gioia.radio.views

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun MainWindow() {
    MaterialTheme {
        Row(modifier = Modifier.padding(16.dp)){
            Finders(
                modifier = Modifier.weight(0.5f),
                text = "",
                onTextChanged = {}
            )
        }
    }
}