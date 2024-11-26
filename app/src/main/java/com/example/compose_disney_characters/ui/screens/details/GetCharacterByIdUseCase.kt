package com.example.compose_disney_characters.ui.screens.details

import com.example.compose_disney_characters.repository.DisneyCharactersListRepository
import com.example.compose_disney_characters.ui.screens.details.domain.DetailsResult
import com.example.compose_disney_characters.utils.toCharacterMainData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: DisneyCharactersListRepository
) {

    suspend fun loadCharacterById(id: Int): Flow<DetailsResult> {
        return flow {
            emit(DetailsResult.Loading)
            val response = repository.getCharacterById(id)
            if (response.isSuccessful) {
                emit(DetailsResult.Success(response.body()?.toCharacterMainData()))
            } else {
                emit(DetailsResult.Error(Throwable(response.message())))
            }
        }.catch { e ->
            emit(DetailsResult.Error(Throwable(e.message)))
        }
    }
}