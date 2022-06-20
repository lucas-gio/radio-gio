package com.gioia.radio.ui.screens.settings

import androidx.compose.runtime.State
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.ui.screens.root.Component

interface SettingsComponent: Component {
    val model: State<SettingsModel>
    var componentContext: ComponentContext?
    var onBackPressed: () -> Unit
}