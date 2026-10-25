package com.example.pagoflex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.pagoflex.ui.screens.FormularioScreen
import com.example.pagoflex.ui.screens.PerfilScreen
import com.example.pagoflex.ui.themes.PagoFlexTheme

enum class Pantalla {
    FORMULARIO,
    PERFIL
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PagoFlexTheme {

                var pantalla by rememberSaveable() {
                    mutableStateOf(Pantalla.FORMULARIO)
                }

                when (pantalla) {
                    Pantalla.FORMULARIO -> {
                        FormularioScreen(
                            onRegistroExitoso = {
                                pantalla = Pantalla.PERFIL
                            }
                        )
                    }

                    Pantalla.PERFIL -> {
                        PerfilScreen()
                    }
                }
            }
        }
    }
}