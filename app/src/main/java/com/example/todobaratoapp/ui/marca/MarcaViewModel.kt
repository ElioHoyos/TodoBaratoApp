package com.example.todobaratoapp.ui.marca

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todobaratoapp.data.repository.MarcaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MarcaViewModel(
    private val repository: MarcaRepository = MarcaRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow<MarcaUiState>(MarcaUiState.Loading)
    val uiState: StateFlow<MarcaUiState> = _uiState.asStateFlow()

    init {
        cargarMarcas()
    }

    fun cargarMarcas() {
        viewModelScope.launch {
            _uiState.value = MarcaUiState.Loading
            _uiState.value = try {
                MarcaUiState.Success(repository.getMarcas())
            } catch (e: IOException) {
                MarcaUiState.Error("No se pudo conectar con el servidor. Verifica tu conexión.")
            } catch (e: Exception) {
                MarcaUiState.Error("Ocurrió un error al cargar las marcas: ${e.message}")
            }
        }
    }
}