package com.example.pagoflex.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pagoflex.ui.styles.EstilosBoton
import com.example.pagoflex.ui.styles.styleBotonAlto
import com.example.pagoflex.ui.themes.PagoFlexTheme

@Composable
fun BotonSecundario(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.styleBotonAlto(),
        shape = EstilosBoton.forma,
        colors = EstilosBoton.coloresSecundarios(),
        border = EstilosBoton.bordeSecundario(),
        contentPadding = EstilosBoton.relleno

    ) {
        Text(text = texto)
    }
}

@Preview(showBackground = true)
@Composable
private fun BotonSecundarioPreview() {
    PagoFlexTheme {
        BotonSecundario(texto = "Limpiar", onClick = {}, modifier = Modifier.padding(16.dp))
    }
}