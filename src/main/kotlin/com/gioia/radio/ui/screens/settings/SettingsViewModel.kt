package com.gioia.radio.ui.screens.settings

import androidx.compose.runtime.State
import com.arkivanov.decompose.ComponentContext

interface SettingsViewModel{
    val model: State<SettingsModel>
    var componentContext: ComponentContext?
    var onBackPressed: () -> Unit
}