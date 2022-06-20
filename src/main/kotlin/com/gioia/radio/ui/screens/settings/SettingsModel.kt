package com.gioia.radio.ui.screens.settings

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.gioia.radio.data.domains.Configuration

@Parcelize
data class SettingsModel(
    var configurations: List<Configuration> = emptyList(),
): Parcelable