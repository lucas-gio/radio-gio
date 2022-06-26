package com.gioia.radio.ui.screens.welcome

import com.gioia.radio.ui.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WelcomeViewModelImpl: ViewModel(){
    companion object {
        const val SPLASH_DELAY = 1000L
    }

    private val _isWelcomeFinished = MutableStateFlow(false)
    val isWelcomeFinished: StateFlow<Boolean> = _isWelcomeFinished

    override fun init(viewModelScope: CoroutineScope) {
        super.init(viewModelScope)

        viewModelScope.launch {
            delay(SPLASH_DELAY)
            _isWelcomeFinished.value = true
        }
    }
}