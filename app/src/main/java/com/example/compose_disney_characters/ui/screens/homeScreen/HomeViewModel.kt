package com.example.compose_disney_characters.ui.screens.homeScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeAction
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeState
import com.example.compose_disney_characters.repository.DisneyCharactersListRepository
import com.example.compose_disney_characters.ui.screens.homeScreen.domain.HomeResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DisneyCharactersListRepository
) : ViewModel() {

    val state = MutableLiveData(HomeState())

    fun processAction(action: HomeAction) {
        when (action) {
            is HomeAction.Init -> loadListData()
            HomeAction.ClearError -> clearError()
        }
    }

    private fun loadListData() {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(state.value?.copy(isLoading = true))
            when (val result = repository.getListCharacters()) {
                is HomeResult.Success -> state.postValue(
                    state.value?.copy(
                        isLoading = false,
                        disneyCharactersList = result.data,
                        error = null
                    )
                )

                is HomeResult.Error -> state.postValue(
                    state.value?.copy(
                        isLoading = false,
                        error = result.throwable.message
                    )
                )
            }
        }
    }

    private fun clearError() {
        state.value?.copy(error = null)
    }
}