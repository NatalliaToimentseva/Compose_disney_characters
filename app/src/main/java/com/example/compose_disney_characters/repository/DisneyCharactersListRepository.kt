package com.example.compose_disney_characters.repository

import com.example.compose_disney_characters.models.CharacterItemModel
import com.example.compose_disney_characters.models.CharacterMainData

interface DisneyCharactersListRepository {

    suspend fun getListCharacters(): List<CharacterItemModel>

    suspend fun getCharacterById(id: Int): CharacterMainData?
}