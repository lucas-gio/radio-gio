package com.gioia.radio.ui.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.consume


class SettingsViewModelImpl(
    stateKeeper: StateKeeper,
) : SettingsViewModel {
    private val _model = mutableStateOf(stateKeeper.consume(key = this.javaClass.name) ?: SettingsModel())
    override val model: State<SettingsModel> = _model
    private val state by model

    init {
        stateKeeper.register(key = "SAVED_STATE") { state }
    }

    override var componentContext: ComponentContext? = null
    override var onBackPressed: () -> Unit = {}
}