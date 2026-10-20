package com.example.pagoflex.model

data class Perfil(
    val nombre: String,
    val correo: String,
    val pagadas: Int,
    val suscripciones: Int
)
data class PerfilUiState(
    val perfil: Perfil,
    val pagaste: Boolean = false
)