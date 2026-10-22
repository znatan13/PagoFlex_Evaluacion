package com.example.pagoflex.ui.styles

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import com.example.pagoflex.ui.themes.Azul
import com.example.pagoflex.ui.themes.Texto


object EstiloPagos {

    val titulo = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
        color = Azul
    )

    val texto = TextStyle(
        fontSize = 16.sp,
        color = Texto
    )

    val tarjeta = RoundedCornerShape(16.dp)
    val boton = RoundedCornerShape(12.dp)
    val espacio = 16.dp
}