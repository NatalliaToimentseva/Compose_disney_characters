package com.example.compose_disney_characters.repository.repositoryImpl

import com.example.compose_disney_characters.dataSources.DisneyApi
import com.example.compose_disney_characters.models.CharacterItemModel
import com.example.compose_disney_characters.models.CharacterMainData
import com.example.compose_disney_characters.repository.DisneyCharactersListRepository
import com.example.compose_disney_characters.utils.toCharacterMainData
import com.example.compose_disney_characters.utils.toListCharacterModel
import javax.inject.Inject

class DisneyCharactersListRetrofitRepository @Inject constructor(
    private val api: DisneyApi
) : DisneyCharactersListRepository {

    override suspend fun getListCharacters(): List<CharacterItemModel> {
        val response = api.getAllCharacters()
        var listCharacters: List<CharacterItemModel>? = null
        if (response.isSuccessful) {
            listCharacters = response.body()?.toListCharacterModel()
        }
        return listCharacters ?: arrayListOf()
    }

    override suspend fun getCharacterById(id: Int): CharacterMainData? {
        val response = api.getCharacter(id)
        var character: CharacterMainData? = null
        if (response.isSuccessful) {
            character = response.body()?.toCharacterMainData()
        }
        return character
    }
}