package com.example.pagoflex.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagoflex.model.Pagos
import com.example.pagoflex.model.PagosUrgentes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PagosViewModel : ViewModel() {

    private val pagosEjemplo = Pagos(
        empresa = "Clavistel",
        monto = 15000,
        vencimiento = "17/11/2026",
        estado = false,
        historial = listOf("17/10/2026","17/9/2026")
    )

    private val _uiState = MutableStateFlow(PagosUrgentes(Pagos = pagosEjemplo))

    val uiState: StateFlow<PagosUrgentes> = _uiState.asStateFlow()

    fun onAlternarPago(){
        _uiState.update { estado ->
            val cambio = if (estado.urgente) -1 else 1
            estado.copy(
                urgente = !estado.urgente,
                pagos =estado.pagos.copy(vencimiento = estado.pagos.vencimiento ) /* Modificar, debe contar (si queda una semana, es urgente) */
            )
        }
    }
}