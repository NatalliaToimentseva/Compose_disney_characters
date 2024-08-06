package com.example.compose_disney_characters.repository

import com.example.compose_disney_characters.ui.screens.details.domain.DetailsResult
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeResult

interface DisneyCharactersListRepository {

    suspend fun getListCharacters(): HomeResult

    suspend fun getCharacterById(id: Int): DetailsResult
}