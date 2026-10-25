package com.example.pagoflex

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.pagoflex.ui.screens.FormularioScreen
import com.example.pagoflex.ui.screens.PagosScreen
import com.example.pagoflex.ui.screens.PerfilScreen
import com.example.pagoflex.ui.themes.PagoFlexTheme


enum class Pantalla { FORMULARIO, PERFIL, SUSCRIBCIONES}

private val PANTALLA_INICIAL = Pantalla.FORMULARIO
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PagoFlexTheme {
                when (PANTALLA_INICIAL) {
                    Pantalla.FORMULARIO -> FormularioScreen()
                    Pantalla.PERFIL -> PerfilScreen()
                    Pantalla.SUSCRIBCIONES -> PagosScreen()
                }
            }
        }
    }
}