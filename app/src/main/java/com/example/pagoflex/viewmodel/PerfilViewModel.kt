package com.example.pagoflex.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagoflex.model.Perfil
import com.example.pagoflex.model.PerfilUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PerfilViewModel : ViewModel(){

    private val perfilEjemplo = Perfil(
        nombre = "Juan",
        correo = "Lopez",
        pagadas = 1,
        suscripciones = 2
    )
    private val _uiState = MutableStateFlow(PerfilUiState(perfil = perfilEjemplo))

    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()


}