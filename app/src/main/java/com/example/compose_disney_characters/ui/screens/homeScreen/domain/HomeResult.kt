package com.example.compose_disney_characters.ui.screens.homeScreen.domain

import com.example.compose_disney_characters.models.CharacterItemModel

sealed class HomeResult {

    data class Success(val data: List<CharacterItemModel>) : HomeResult()

    data class Error(val throwable: Throwable) : HomeResult()
}