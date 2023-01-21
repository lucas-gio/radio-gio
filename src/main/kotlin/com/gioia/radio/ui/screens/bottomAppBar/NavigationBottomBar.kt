package com.gioia.radio.ui.screens.bottomAppBar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.gioia.radio.ui.navigation.RootComponent
import com.gioia.radio.ui.themes.Globals.Companion.iconSize

@Composable
fun NavigationBottomBar(
    rootComponent: RootComponent
){
    val stack by rootComponent.childStack.subscribeAsState()
    val activeComponent = stack.active.instance

    BottomNavigation {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Radio,
                    contentDescription = "radios",
                    modifier = iconSize
                )
            },
            selected = activeComponent is RootComponent.Child.Stations,
            onClick = rootComponent::onRadioNavigationItem
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    modifier = iconSize
                )
            },
            selected = activeComponent is RootComponent.Child.Search,
            onClick = rootComponent::onSearchNavigationItem
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite",
                    modifier = iconSize
                )
            },
            selected = activeComponent is RootComponent.Child.Favorites,
            onClick = rootComponent::onFavoriteNavigationItem
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "settings",
                    modifier = iconSize
                )
            },
            selected = activeComponent is RootComponent.Child.Settings,
            onClick = rootComponent::onSettingsNavigationItem
        )
    }
}