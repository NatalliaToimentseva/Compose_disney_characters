package com.example.compose_disney_characters.ui.screens.homeScreen.domain

import com.example.compose_disney_characters.models.CharacterItemModel

private const val TEMP_URL =
    "https://static.wikia.nocookie.net/disney/images/1/15/Arianna_Tangled.jpg/revision/latest?cb=20160715191802"

data class HomeState(
    val isLoading: Boolean = false,
    val disneyCharactersList: List<CharacterItemModel> = arrayListOf(
        CharacterItemModel(1, "Test1", TEMP_URL),
        CharacterItemModel(2, "Test2", TEMP_URL),
        CharacterItemModel(3, "Test3", TEMP_URL),
        CharacterItemModel(4, "Test4", TEMP_URL),
        CharacterItemModel(5, "Test5", TEMP_URL),
        CharacterItemModel(6, "Test6", TEMP_URL),
        CharacterItemModel(7, "Test7", TEMP_URL)
    ),
    val error: String? = null
)
