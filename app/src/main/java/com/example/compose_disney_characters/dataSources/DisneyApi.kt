package com.example.compose_disney_characters.dataSources

import com.example.compose_disney_characters.dataSources.entities.CharacterEntity
import com.example.compose_disney_characters.dataSources.entities.ListCharactersEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DisneyApi {

    @GET("character")
    suspend fun getAllCharacters(): Response<ListCharactersEntity>

    @GET("character/{id}")
    suspend fun getCharacter(
        @Path("id") id: Int
    ): Response<CharacterEntity>
}