package com.example.pagoflex.model

data class FormularioUiState(
    val nombre: String = "",
    val correo: String = "",
    val contrasenia: String = "",
    val recibirNotificaciones: Boolean = false,
    val aceptarTerminos: Boolean = false,

    val contraseniaVisible: Boolean = false,

    val errorNombre: String? = null,
    val errorCorreo: String? = null,
    val errorContrasenia: String? = null,
    val errorTerminos: String? = null,

    val registroExitoso: Boolean = false
    )