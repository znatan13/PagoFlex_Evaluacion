package com.example.pagoflex.model


data class Pagos (
    val empresa: String,
    val monto: Int,
    val vencimiento: String,
    val estado: Boolean,
    val historial: List<String>
)

data class PagosUrgentes (
    val pagos: Pagos,
    val urgente: Boolean = false
)