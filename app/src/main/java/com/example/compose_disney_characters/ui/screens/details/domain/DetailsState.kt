package com.example.compose_disney_characters.ui.screens.details.domain

import androidx.lifecycle.MutableLiveData
import com.example.compose_disney_characters.models.CharacterMainData

data class DetailsState(
    val isInProgress: Boolean = false,
    val character: CharacterMainData? = null
)
