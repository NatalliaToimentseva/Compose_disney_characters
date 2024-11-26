package com.example.compose_disney_characters.dataSources.entities

import com.google.gson.annotations.SerializedName

data class CharacterData(
    @SerializedName("_id")
    val id: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("name")
    val name: String
)
