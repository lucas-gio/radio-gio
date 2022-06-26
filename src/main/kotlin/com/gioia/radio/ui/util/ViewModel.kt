package com.gioia.radio.ui.util

import kotlinx.coroutines.CoroutineScope

open class ViewModel {
    private lateinit var viewModelScope: CoroutineScope

    open fun init(viewModelScope: CoroutineScope) {
        this.viewModelScope = viewModelScope
    }
}