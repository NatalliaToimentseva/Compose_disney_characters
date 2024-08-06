package com.example.compose_disney_characters.utils

import com.example.compose_disney_characters.dataSources.entities.CharacterEntity
import com.example.compose_disney_characters.dataSources.entities.ListCharactersEntity
import com.example.compose_disney_characters.models.CharacterFieldsModel
import com.example.compose_disney_characters.models.CharacterItemModel
import com.example.compose_disney_characters.models.CharacterMainData

private const val CHARACTER_NAME = "name"
private const val CHARACTER_IMAGE_URL = "imageUrl"

fun ListCharactersEntity.toListCharacterModel(): List<CharacterItemModel> {
    val characterList = ArrayList<CharacterItemModel>()
    for (item in this.data) {
        characterList.add(
            CharacterItemModel(
                id = item.id,
                name = item.name,
                imageUrl = item.imageUrl
            )
        )
    }
    return characterList
}

fun CharacterEntity.toCharacterMainData(): CharacterMainData {
    val list = arrayListOf<CharacterFieldsModel>()
    var cName = ""
    var url = ""
    this.data.forEach { (name, data) ->
        if (name == CHARACTER_NAME) {
            cName = data.toString()
        } else if (name == CHARACTER_IMAGE_URL) {
            url = data.toString()
        } else {
            val subList = if (data is List<*>) data.map { it.toString() } else arrayListOf()
            if (subList.isNotEmpty()) {
                list.add(
                    CharacterFieldsModel(
                        name = name,
                        movies = subList
                    )
                )
            }
        }
    }
    return CharacterMainData(
        name = cName,
        imgUrl = url,
        fields = list
    )
}