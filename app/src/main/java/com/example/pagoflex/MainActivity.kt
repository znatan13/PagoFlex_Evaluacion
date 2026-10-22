package com.example.pagoflex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pagoflex.ui.screens.FormularioScreen
import com.example.pagoflex.ui.screens.PagosScreen
import com.example.pagoflex.ui.screens.PerfilScreen


enum class Pantalla { FORMULARIO, PERFIL, SUSCRIBCIONES}

private val PANTALLA_INICIAL = Pantalla.FORMULARIO
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            /* tema principal (themes.Theme)*/ {
                when (PANTALLA_INICIAL) {
                    Pantalla.FORMULARIO -> FormularioScreen()
                    Pantalla.PERFIL -> PerfilScreen()
                    Pantalla.SUSCRIBCIONES -> PagosScreen()
                }
            }
        }
    }
}