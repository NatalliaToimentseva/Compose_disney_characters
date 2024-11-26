package com.example.compose_disney_characters.ui.navigation

sealed class ScreenRoute(val route: String) {

    data object Home : ScreenRoute("Home")

    data object Details : ScreenRoute("Details/{id}") {
        fun selectRoute(id: Int): String {
            return "Details/$id"
        }
    }
}