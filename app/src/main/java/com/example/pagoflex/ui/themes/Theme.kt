package com.example.pagoflex.ui.themes

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val ColoresPago = lightColorScheme(
    primary = Azul,
    onPrimary = Blanco,
    secondary = AzulClaro,
    onSecondary = Texto,
    background = Fondo,
    onBackground = Texto,
    surface = Blanco,
    onSurface = Texto
)

private val Formas = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(20.dp)
)

@Composable
fun PagoFlexTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = ColoresPago,
        typography = PagoFlexTypography,
        shapes = Formas,
        content = content
    )
}