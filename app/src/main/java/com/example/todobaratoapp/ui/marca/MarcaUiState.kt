package com.example.todobaratoapp.ui.marca

import com.example.todobaratoapp.data.model.Marca

sealed interface MarcaUiState {
    data object Loading : MarcaUiState
    data class Success(val marcas : List<Marca>) : MarcaUiState
    data class Error(val message: String) : MarcaUiState
}