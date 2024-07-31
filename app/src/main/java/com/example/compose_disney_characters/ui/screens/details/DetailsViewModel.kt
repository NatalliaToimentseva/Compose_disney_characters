package com.example.compose_disney_characters.ui.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_disney_characters.repository.DisneyCharactersListRepository
import com.example.compose_disney_characters.ui.screens.details.domain.DetailsAction
import com.example.compose_disney_characters.ui.screens.details.domain.DetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DisneyCharactersListRepository
) : ViewModel() {

    val state = MutableLiveData(DetailsState())

    fun processAction(action: DetailsAction) {
        when (action) {
            is DetailsAction.Init -> getCharacter(action.id)
        }
    }

    private fun getCharacter(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            state.postValue(state.value?.copy(isInProgress = true))
            val character = repository.getCharacterById(id)
            state.postValue(state.value?.copy(character = character, isInProgress = false))
        }
    }
}