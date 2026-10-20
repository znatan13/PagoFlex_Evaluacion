package com.example.pagoflex.viewmodel

import androidx.lifecycle.ViewModel
import com.example.pagoflex.model.Pagos
import kotlinx.coroutines.flow.MutableStateFlow

class PagosViewModel : ViewModel() {

    private val pagosEjemplo = Pagos(
        empresa = "Clavistel",
        monto = 15000,
        vencimiento = "17/11/2026",
        estado = false,
        historial = listOf("17/10/2026","17/9/2026")
    )

    privat val _uiState = MutableStateFlow()
}