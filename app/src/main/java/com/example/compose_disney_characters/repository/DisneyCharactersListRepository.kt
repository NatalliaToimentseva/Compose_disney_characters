package com.example.compose_disney_characters.repository

import com.example.compose_disney_characters.dataSources.entities.CharacterEntity
import com.example.compose_disney_characters.dataSources.entities.ListCharactersEntity
import retrofit2.Response

interface DisneyCharactersListRepository {

    suspend fun getListCharacters(): Response<ListCharactersEntity>

    suspend fun getCharacterById(id: Int): Response<CharacterEntity>
}