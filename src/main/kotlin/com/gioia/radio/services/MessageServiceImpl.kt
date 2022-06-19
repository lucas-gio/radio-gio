package com.gioia.radio.services

import com.gioia.radio.data.enums.ConfigKey
import com.gioia.radio.data.enums.Locales
import com.gioia.radio.data.repositories.ConfigurationRepository
import java.util.*

class MessageServiceImpl(
    private val configurationRepository: ConfigurationRepository,
    var configuredLocale: String? = configurationRepository.find(ConfigKey.Locale.toString())?.value
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
        //val configuratedLocale: String = configuredLocale  configurationRepository.find(ConfigKey.Locale.toString())?.value
        val locale = Locale
            .Builder()
            .setLanguage(
                if(configuredLocale.isNullOrEmpty()) Locales.EN.toString() else configuredLocale
            )
            .build()

        return ResourceBundle.getBundle(resourcesName, locale)
    }
}