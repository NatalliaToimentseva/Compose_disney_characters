package com.example.compose_disney_characters.ui.screens.homeScreen.domain

import com.example.compose_disney_characters.repository.DisneyCharactersListRepository
import com.example.compose_disney_characters.utils.toListCharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadListDataUseCase @Inject constructor(
    private val repository: DisneyCharactersListRepository
) {

    fun loadAllCharacters(): Flow<HomeResult> {
        return flow {
            emit(HomeResult.Loading)
            val response = repository.getListCharacters()
            if (response.isSuccessful) {
                emit(HomeResult.Success(response.body()?.toListCharacterModel() ?: arrayListOf()))
            } else {
                emit(HomeResult.Error(Throwable(response.message())))
            }
        }.catch { e ->
            emit(HomeResult.Error(Throwable(e.message)))
        }
    }
}