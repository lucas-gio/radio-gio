package com.gioia.radio.data.repositories

import com.gioia.radio.data.domains.Configuration

interface ConfigurationRepository {
    fun upsert(configuration: Configuration)
    fun find(key: String): Configuration?
    fun createIndexes()
}