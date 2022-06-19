package com.gioia.radio.ui.screens.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Radio
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem (
    val onClick: () -> Unit = {},
    val title: String,
    val icon: ImageVector
){
    object Radios : NavigationItem(
        title = "radios", //TODO
        icon = Icons.Default.Radio
    )

    object Search : NavigationItem(
        title = "search", //TODO
        icon = Icons.Default.Search
    )

    object Favourites : NavigationItem(
        title = "favourites", //TODO
        icon = Icons.Default.Favorite
    )

    object Settings : NavigationItem(
        title = "settings", //TODO
        icon = Icons.Default.Settings
    )
}