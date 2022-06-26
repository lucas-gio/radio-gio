package com.gioia.radio.ui.screens.root.topAppBar

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

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