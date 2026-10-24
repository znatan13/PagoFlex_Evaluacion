package com.example.pagoflex.ui.styles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.pagoflex.ui.themes.Azul
import com.example.pagoflex.ui.themes.AzulClaro
import com.example.pagoflex.ui.themes.Dimens
import com.example.pagoflex.ui.themes.Texto

object EstilosBoton {

    val forma: Shape = RoundedCornerShape(Dimens.radioBoton)

    val relleno = PaddingValues(horizontal = 20.dp, vertical = 12.dp)

    @Composable
    fun coloresPrincipales(): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Azul,
        contentColor = Texto,
        disabledContainerColor = AzulClaro,
        disabledContentColor = Texto
    )

    @Composable
    fun coloresSecundarios(): ButtonColors = ButtonDefaults.outlinedButtonColors(
        contentColor = MaterialTheme.colorScheme.primary
    )

    @Composable
    fun bordeSecundario(): BorderStroke =
        BorderStroke(Dimens.bordeBoton, MaterialTheme.colorScheme.primary)

    @Composable
    fun elevacion(): ButtonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = Dimens.elevacionesBoton,
        pressedElevation = 1.dp
    )
}

fun Modifier.styleBotonAlto(): Modifier = this.height(Dimens.AlturaBoton)
