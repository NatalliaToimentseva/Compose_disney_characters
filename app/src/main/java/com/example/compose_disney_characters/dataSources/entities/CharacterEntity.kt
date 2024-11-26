package com.example.compose_disney_characters.dataSources.entities

import com.google.gson.annotations.SerializedName

data class CharacterEntity(
    @SerializedName("data")
    val data: HashMap<String, Any>
)
