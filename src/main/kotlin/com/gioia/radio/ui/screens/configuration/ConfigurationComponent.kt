package com.gioia.radio.ui.screens.configuration

import androidx.compose.runtime.State
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.ui.screens.root.Component

interface ConfigurationComponent: Component {
    val model: State<ConfigurationModel>
    var componentContext: ComponentContext?
    var onBackPressed: () -> Unit
}