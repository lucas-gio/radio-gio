package com.gioia.radio.ui.screens.welcome

import androidx.compose.runtime.*
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.ui.navigation.Component


class WelcomeComponent(
    private val componentContext: ComponentContext,
    private val onWelcomeFinished: () -> Unit,
    private val welcomeViewModel: WelcomeViewModelImpl,
) : Component, ComponentContext by componentContext {

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(welcomeViewModel) {
            welcomeViewModel.init(scope)
        }

        val isWelcomeFinished by welcomeViewModel.isWelcomeFinished.collectAsState()

        if (isWelcomeFinished) {
            onWelcomeFinished()
        }

        Welcome(
            viewModel = welcomeViewModel
        )
    }
}