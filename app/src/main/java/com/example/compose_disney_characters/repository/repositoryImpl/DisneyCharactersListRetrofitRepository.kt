package com.example.compose_disney_characters.repository.repositoryImpl

import com.example.compose_disney_characters.dataSources.DisneyApi
import com.example.compose_disney_characters.repository.DisneyCharactersListRepository
import com.example.compose_disney_characters.ui.screens.details.domain.DetailsResult
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeResult
import com.example.compose_disney_characters.utils.toCharacterMainData
import com.example.compose_disney_characters.utils.toListCharacterModel
import javax.inject.Inject

class DisneyCharactersListRetrofitRepository @Inject constructor(
    private val api: DisneyApi
) : DisneyCharactersListRepository {

    override suspend fun getListCharacters(): HomeResult {
        val response = api.getAllCharacters()
        return if (response.isSuccessful) {
            HomeResult.Success(response.body()?.toListCharacterModel() ?: arrayListOf())
        } else {
            HomeResult.Error(Throwable(response.message()))
        }
    }

    override suspend fun getCharacterById(id: Int): DetailsResult {
        val response = api.getCharacter(id)
        return if (response.isSuccessful) {
            DetailsResult.Success(response.body()?.toCharacterMainData())
        } else {
            DetailsResult.Error(Throwable(response.message()))
        }
    }
}
