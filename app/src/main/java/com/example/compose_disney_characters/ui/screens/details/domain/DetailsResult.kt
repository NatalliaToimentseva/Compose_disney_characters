package com.example.compose_disney_characters.ui.screens.details.domain

import com.example.compose_disney_characters.models.CharacterMainData

sealed class DetailsResult {

    data class Success(val data: CharacterMainData?) : DetailsResult()

    data class Error(val throwable: Throwable) : DetailsResult()
}