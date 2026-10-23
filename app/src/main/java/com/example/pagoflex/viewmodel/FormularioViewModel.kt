package com.example.pagoflex.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagoflex.model.FormularioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FormularioViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FormularioUiState())

    val uiState: StateFlow<FormularioUiState> = _uiState.asStateFlow()

    fun onNombreCambia(valor: String) {
        _uiState.update { it.copy(nombre = valor, errorNombre = null, registroExitoso = false)
        }
    }
    fun onCorreoCambia(valor: String){
        _uiState.update { it.copy(correo = valor.trim(), errorCorreo = null, registroExitoso = false )}
    }
    fun onContraseniaCambia(valor: String){
        _uiState.update { it.copy(contrasenia = valor, errorContrasenia = null, registroExitoso = false) }
    }
    fun onAlternarVisibilidad(){
        _uiState.update { it.copy(contraseniaVisible = !it.contraseniaVisible) }
    }


    fun onRegistrar() {
        val estado = _uiState.value

        val errorNombre = if (estado.nombre.isBlank())
            "El nombre es obligatorio" else null

        val errorCorreo = if (!estado.correo.contains("@") || !estado.correo.contains("."))
            "Ingrese un correo valido" else null

        val errorContrasenia = if (estado.contrasenia.length < 6)
            "La contraseña debe incluir minimo 6 caracteres" else null

        val errorTerminos = if (!estado.aceptarTerminos)
            "Debes aceptar los terminos y condiciones" else null

        val hayErrores =
            listOf(errorNombre, errorCorreo, errorContrasenia, errorTerminos).any { it != null }

        _uiState.update {
            it.copy(
                errorNombre = errorNombre,
                errorCorreo = errorCorreo,
                errorContrasenia = errorContrasenia,
                errorTerminos = errorTerminos,
                registroExitoso = !hayErrores
            )
        }
    }
        fun onLimpiar() {
            _uiState.value = FormularioUiState()
        }
}