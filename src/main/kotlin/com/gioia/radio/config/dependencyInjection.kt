package com.gioia.radio.config

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import com.gioia.radio.data.repositories.ConfigurationRepository
import com.gioia.radio.data.repositories.ConfigurationRepositoryImpl
import com.gioia.radio.data.repositories.CountryRepository
import com.gioia.radio.data.repositories.CountryRepositoryImpl
import com.gioia.radio.services.MessageService
import com.gioia.radio.services.MessageServiceImpl
import com.gioia.radio.services.PlayerService
import com.gioia.radio.services.PlayerServiceImpl
import com.gioia.radio.tools.DatabaseGenerator
import com.gioia.radio.tools.DatabaseGeneratorImpl
import com.gioia.radio.ui.screens.main.MainComponent
import com.gioia.radio.ui.screens.main.MainComponentImpl
import com.gioia.radio.ui.screens.settings.SettingsComponent
import com.gioia.radio.ui.screens.settings.SettingsComponentImpl
import org.dizitart.no2.Nitrite
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.direct
import org.kodein.di.instance
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent
import java.io.File


val di = DI {
    bindSingleton<DatabaseGenerator> {DatabaseGeneratorImpl(instance(), instance())}
    bindSingleton<CountryRepository> {CountryRepositoryImpl(instance())}
    bindSingleton<ConfigurationRepository> {ConfigurationRepositoryImpl(instance())}
    bindSingleton<PlayerService> {PlayerServiceImpl(instance())}
    bindSingleton<MessageService> {MessageServiceImpl(instance())}
    bindSingleton {AudioPlayerComponent()}
    bindSingleton<StateKeeper> { StateKeeperDispatcher() }
    bindSingleton<MainComponent> { MainComponentImpl(instance(), instance(), instance(), instance()) }
    bindSingleton<SettingsComponent> { SettingsComponentImpl(instance()) }

    //val bundle: ResourceBundle = ResourceBundle.getBundle("Messages")
    bindSingleton<Nitrite>{
        Nitrite
            .builder()
            .filePath(".${File.separator}file.db")
            .openOrCreate()
    }
}

val dk = di.direct