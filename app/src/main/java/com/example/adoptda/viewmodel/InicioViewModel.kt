package com.example.adoptda.viewmodel

import androidx.lifecycle.ViewModel
import com.example.adoptda.model.InicioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InicioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(InicioUiState())
    val uiState: StateFlow<InicioUiState> = _uiState.asStateFlow()

    fun onComenzarClick() {
        // Aquí puedes agregar lógica adicional si es necesario antes de navegar
        _uiState.value = _uiState.value.copy(shouldNavigateToMenu = true)
    }

    fun onNavigatedToMenu() {
        _uiState.value = _uiState.value.copy(shouldNavigateToMenu = false)
    }
}
