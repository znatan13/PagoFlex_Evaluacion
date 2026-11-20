package com.example.pagoflex.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagoflex.model.Perfil
import com.example.pagoflex.model.PerfilUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PerfilViewModel : ViewModel(){
    private var contrasenia = "#Test123"
    private val perfilEjemplo = Perfil(
        nombre = "Juan Lopez",
        correo = "J.lopez@gmail.com",
        pagadas = 1,
        suscripciones = 2
    )
    private val _uiState = MutableStateFlow(PerfilUiState(perfil = perfilEjemplo))

    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    fun obtenerContrasenia(): String = contrasenia

    fun actualizarPerfil(nombre: String, correo: String, nuevaContrasenia: String) {
        _uiState.update { estado ->
            estado.copy(
                perfil = estado.perfil.copy(
                    nombre = nombre,
                    correo = correo
                )
            )
        }
        contrasenia = nuevaContrasenia
    }
}