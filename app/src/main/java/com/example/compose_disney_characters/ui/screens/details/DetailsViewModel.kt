package com.example.compose_disney_characters.ui.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_disney_characters.ui.screens.details.domain.DetailsAction
import com.example.compose_disney_characters.ui.screens.details.domain.DetailsResult
import com.example.compose_disney_characters.ui.screens.details.domain.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    val state = MutableStateFlow(DetailsState())

    fun processAction(action: DetailsAction) {
        when (action) {
            is DetailsAction.Init -> getCharacter(action.id)
            DetailsAction.ClearError -> clearError()
        }
    }

    private fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getCharacterByIdUseCase.loadCharacterById(id).collect { result ->
                handleResult(result)
            }
        }
    }

    private fun handleResult(result: DetailsResult) {
        when (result) {
            is DetailsResult.Loading -> state.tryEmit(
                state.value.copy(
                    isInProgress = true
                )
            )

            is DetailsResult.Success -> state.tryEmit(
                state.value.copy(
                    isInProgress = false,
                    character = result.data,
                    error = null
                )
            )

            is DetailsResult.Error -> state.tryEmit(
                state.value.copy(
                    isInProgress = false,
                    error = result.throwable.message
                )
            )
        }
    }

    private fun clearError() {
        state.tryEmit(state.value.copy(error = null))
    }
}