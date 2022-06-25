package com.gioia.radio.ui.screens.root.topAppBar

import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Radio
import androidx.compose.runtime.Composable
import com.gioia.radio.ui.screens.root.RootComponent

@Composable
fun NavigationTopBar() {
    Text(text = "<Nombre de radio>")
/*
    Button(
        icon = {
            Icon(
                imageVector = Icons.Default.Radio,
                contentDescription = "radios"
            )
        },
        label = { Text(text = "radios")},
        selected = activeComponent is RootComponent.Child.Radio,
        onClick = rootComponent::onRadioNavigationItem
    )*/
}