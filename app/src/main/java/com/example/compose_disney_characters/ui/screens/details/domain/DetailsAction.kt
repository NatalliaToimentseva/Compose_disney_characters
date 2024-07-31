package com.example.compose_disney_characters.ui.screens.details.domain

sealed class DetailsAction {

    data class Init(val id: Int) : DetailsAction()
}