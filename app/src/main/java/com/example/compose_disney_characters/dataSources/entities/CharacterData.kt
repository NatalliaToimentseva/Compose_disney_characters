package com.example.compose_disney_characters.dataSources.entities

import com.google.gson.annotations.SerializedName

data class CharacterData(
    @SerializedName("_id")
    val id: Int,
    val imageUrl: String,
    val name: String
)
