package com.example.compose_disney_characters.ui.screens.homeScreen.domain

import com.example.compose_disney_characters.ui.screens.details.domain.DetailsAction

sealed class HomeAction {

    data object Init : HomeAction()
    data object ClearError : HomeAction()
}
