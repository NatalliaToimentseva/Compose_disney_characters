package com.example.compose_disney_characters.ui.screens.homeScreen.domain

import com.example.compose_disney_characters.models.CharacterItemModel

data class HomeState(
    val isLoading: Boolean = false,
    val disneyCharactersList: List<CharacterItemModel> = arrayListOf()
)
