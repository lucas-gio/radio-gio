package com.gioia.radio.tools

import com.gioia.radio.data.domains.Configuration
import com.gioia.radio.data.enums.ConfigKey
import com.gioia.radio.data.enums.Locales
import com.gioia.radio.data.repositories.ConfigurationRepository

abstract class DatabaseGenerator(
    private val configurationRepository: ConfigurationRepository,
) {
    fun generateDatabase(){
        parseDataFile()
        initConfigurations()
    }

    protected abstract fun parseDataFile()

    private fun initConfigurations(){
        listOf(
            Configuration(ConfigKey.Locale.toString(), Locales.EN.toString(), "Selected locale for translations"),
            Configuration(ConfigKey.Volume.toString(), "50", "Last volume used"),
        )
        .forEach(configurationRepository::upsert)
        configurationRepository.createIndexes()
    }
}