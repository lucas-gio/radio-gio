package com.gioia.radio.config

import org.kodein.di.DI
import org.kodein.di.bindConstant


val di = DI {
    //bindSingleton<IDatabaseService>(tag= "IDatabaseService") {DatabaseService()}
    bindConstant(tag = "defaultHeight") {500}
    bindConstant(tag = "defaultwidth") {900}
}