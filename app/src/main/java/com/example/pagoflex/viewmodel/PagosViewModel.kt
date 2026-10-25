package com.example.pagoflex.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.pagoflex.model.Pagos
import com.example.pagoflex.model.PagosUrgentes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class PagosViewModel : ViewModel() {

    private val pagosEjemplo = Pagos(
        empresa = "Clavistel",
        monto = 15000,
        vencimiento = "17/11/2026",
        estado = false,
        historial = listOf("17/10/2026","17/9/2026")
    )

    private val _uiState = MutableStateFlow(PagosUrgentes(pagos = pagosEjemplo))

    val uiState: StateFlow<PagosUrgentes> = _uiState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAlternarPago(){

        val formato = DateTimeFormatter.ofPattern("dd/mm/yyyy")
        val hoy = LocalDate.now()

        _uiState.update { estado ->
            val vencimiento = LocalDate.parse(
                estado.pagos.vencimiento,
                formato
            )
            val diasTermina = ChronoUnit.DAYS.between(
                hoy,
                vencimiento
            )
            estado.copy(
                urgente = diasTermina <= 10
            )
        }

    }
    fun onPagado() {
        _uiState.update { estado ->
            estado.copy(
                pagos = estado.pagos.copy(
                    estado = true
                )
            )
        }
    }
}