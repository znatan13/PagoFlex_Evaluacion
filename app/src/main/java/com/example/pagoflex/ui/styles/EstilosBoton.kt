package com.example.pagoflex.ui.styles

import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pagoflex.ui.themes.Azul
import com.example.pagoflex.ui.themes.AzulClaro
import com.example.pagoflex.ui.themes.Blanco
import com.example.pagoflex.ui.themes.Dimens

object EstilosBoton {
    val forma: Shape = RoundedCornerShape(Dimens.radioBoton)

    val relleno = PaddingValues(horizontal = 20.dp, vertical = 12.dp)


    @Composable
    fun coloresPrincipal(): ButtonColors = ButtonDefaults.buttonColors(
        containerColor = Azul,
        contentColor = AzulClaro,
        disabledContentColor = Blanco
    )
}

fun Modifier.estiloAltoBoton(): Modifier = this.height(Dimens.alturaBoton)