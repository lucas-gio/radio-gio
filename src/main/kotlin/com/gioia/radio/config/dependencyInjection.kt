package com.gioia.radio.config

import com.gioia.radio.data.repositories.CountryRepository
import com.gioia.radio.data.repositories.CountryRepositoryImpl
import com.gioia.radio.tools.DatabaseGenerator
import com.gioia.radio.tools.DatabaseGeneratorImpl
import org.kodein.di.DI
import org.kodein.di.bindConstant
import org.kodein.di.bindSingleton
import org.kodein.di.instance


val di = DI {
    bindSingleton<DatabaseGenerator> {DatabaseGeneratorImpl(instance())}
    bindSingleton<CountryRepository> {CountryRepositoryImpl()}
    bindConstant(tag = "defaultHeight") {500}
    bindConstant(tag = "defaultwidth") {900}
}