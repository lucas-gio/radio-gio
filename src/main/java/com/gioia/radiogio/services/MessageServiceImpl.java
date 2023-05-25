package com.gioia.radiogio.services;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
class MessageServiceImpl implements MessageService{
    //private val configurationRepository: ConfigurationRepository,
    //var configuredLocale: String? = configurationRepository.find(ConfigKey.Locale.toString())?.value
    //private val resourcesName = "messages"
    @Override
     public String msg(String message) {
        /*return try {
            getResourceBundle()
                .getString(message)
        } catch (e: Exception){
            message
        }*/
        return "";
    }

    /*
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

     */
}