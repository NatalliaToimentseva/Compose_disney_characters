package com.example.compose_disney_characters.dataSources.entities

import com.google.gson.annotations.SerializedName

data class ListCharactersEntity(
    @SerializedName("data")
    val data: List<CharacterData>
)
