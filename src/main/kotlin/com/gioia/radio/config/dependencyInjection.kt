package com.gioia.radio.config

import com.arkivanov.essenty.statekeeper.StateKeeper
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import com.gioia.radio.data.repositories.*
import com.gioia.radio.services.MessageService
import com.gioia.radio.services.MessageServiceImpl
import com.gioia.radio.services.PlayerService
import com.gioia.radio.services.PlayerServiceImpl
import com.gioia.radio.tools.DatabaseGenerator
import com.gioia.radio.tools.DatabaseGeneratorImpl
import com.gioia.radio.ui.screens.settings.SettingsViewModel
import com.gioia.radio.ui.screens.settings.SettingsViewModelImpl
import com.gioia.radio.ui.screens.stations.FavoriteStationsViewModelImpl
import com.gioia.radio.ui.screens.stations.SearchViewModelImpl
import com.gioia.radio.ui.screens.stations.StationsViewModel
import com.gioia.radio.ui.screens.stations.StationsViewModelImpl
import com.gioia.radio.ui.screens.welcome.WelcomeViewModelImpl
import org.dizitart.no2.Nitrite
import org.kodein.di.*
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent

val di = DI {
    bindSingleton<DatabaseGenerator> {DatabaseGeneratorImpl(instance(), instance(), instance())}
    bindSingleton<CountryRepository> {CountryRepositoryImpl(instance())}
    bindSingleton<RadioStationRepository> { RadioStationRepositoryImpl(instance()) }
    bindSingleton<ConfigurationRepository> {ConfigurationRepositoryImpl(instance())}
    bindSingleton<PlayerService> {PlayerServiceImpl(instance(), instance())}
    bindSingleton<MessageService> {MessageServiceImpl(instance())}
    bindSingleton {AudioPlayerComponent()}
    bindSingleton<StateKeeper> { StateKeeperDispatcher() }
    bindSingleton<StationsViewModel> { StationsViewModelImpl(instance(), instance(), instance(), instance()) }
    bind<FavoriteStationsViewModelImpl> (tag = "favorites") with factory {FavoriteStationsViewModelImpl(instance(), instance(), instance(), instance())}
    bind<SearchViewModelImpl> (tag = "search") with factory { SearchViewModelImpl(instance(), instance(), instance(), instance(), instance()) }
    //bindSingleton<SearchViewModel> { SearchViewModelImpl(instance(), instance()) }
    bindSingleton<SettingsViewModel> { SettingsViewModelImpl(instance()) }
    bindSingleton { WelcomeViewModelImpl() }

    //val bundle: ResourceBundle = ResourceBundle.getBundle("Messages")
    bindSingleton<Nitrite>{
        /*val filePath = if(App.isInitDatabase) {
            val s = File.separator
            ".${s}src${s}main${s}resources${s}common${s}file.db"
        }
        else{
            File(System.getProperty("compose.application.resources.dir"))
                .resolve("file.db")
                .absolutePath
        } */

        Nitrite
            .builder()
            //.filePath(filePath)
            //.filePath("C:${File.separator}file.db")
            .filePath("/home/bravo/file.db")
            .openOrCreate()
    }
}

val dk = di.direct