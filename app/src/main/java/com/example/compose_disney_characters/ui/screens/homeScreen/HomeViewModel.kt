package com.example.compose_disney_characters.ui.screens.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeAction
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeState
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val loadListDataUseCase: LoadListDataUseCase
) : ViewModel() {

    val state = MutableStateFlow(HomeState())

    init {
        processAction(HomeAction.Init)
    }

    fun processAction(action: HomeAction) {
        when (action) {
            is HomeAction.Init -> loadListData()
            HomeAction.ClearError -> clearError()
        }
    }

    private fun loadListData() {
        viewModelScope.launch(Dispatchers.IO) {
            loadListDataUseCase.loadAllCharacters().collect { result ->
                handleResult(result)
            }
        }
    }

    private fun handleResult(result: HomeResult) {
        when (result) {
            is HomeResult.Loading -> state.tryEmit(
                state.value.copy(
                    isLoading = true
                )
            )

            is HomeResult.Success -> state.tryEmit(
                state.value.copy(
                    isLoading = false,
                    disneyCharactersList = result.data,
                    error = null
                )
            )

            is HomeResult.Error -> state.tryEmit(
                state.value.copy(
                    isLoading = false,
                    error = result.throwable.message
                )
            )
        }
    }

    private fun clearError() {
        state.tryEmit(state.value.copy(error = null))
    }
}