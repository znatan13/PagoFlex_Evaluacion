package com.example.pagoflex.ui.themes

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

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

@Composable
fun PagoFlexTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColoresPago,
        typography = PagoFlexTypography,
        content = content
    )
}