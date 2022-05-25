package com.gioia.radio.services

import com.gioia.radio.data.enums.ConfigKey
import com.gioia.radio.data.repositories.ConfigurationRepository
import java.util.*

class MessageServiceImpl(
    private val configurationRepository: ConfigurationRepository
) : MessageService {
    private val resourcesName = "messages"

    override fun msg(message: String): String {
        return try {
            getResourceBundle()
                .getString(message)
        } catch (e: Exception){
            message
        }
    }

    private fun getResourceBundle(): ResourceBundle{
        val configuratedLocale = configurationRepository.find(ConfigKey.Locale.toString())?.value
        val locale = Locale
            .Builder()
            .setLanguage(
                if(configuratedLocale.isNullOrEmpty()) "en" else configuratedLocale
            )
            .build()

        return ResourceBundle.getBundle(resourcesName, locale)
    }
}