package com.example.compose_disney_characters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.compose_disney_characters.ui.screens.homeScreen.HomeDestination
import com.example.compose_disney_characters.ui.theme.Compose_disney_charactersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_disney_charactersTheme {
                HomeDestination()
            }
        }
    }
}