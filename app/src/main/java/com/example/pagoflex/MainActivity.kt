
package com.example.pagoflex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.pagoflex.ui.screens.FormularioScreen
import com.example.pagoflex.ui.screens.PerfilScreen
import com.example.pagoflex.ui.screens.PagosScreen
import com.example.pagoflex.ui.screens.LoginScreen
import com.example.pagoflex.ui.screens.RecuperarContraseniaScreen
import com.example.pagoflex.model.Pagos
import com.example.pagoflex.ui.screens.EditarPerfilScreen
import com.example.pagoflex.ui.themes.PagoFlexTheme

enum class Pantalla {
    FORMULARIO,
    LOGIN,
    PERFIL,
    PAGOS,
    RECUPERAR_CONTRASENIA,
    EDITAR_PERFIL
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PagoFlexTheme {

                var pantalla by rememberSaveable {
                    mutableStateOf(Pantalla.FORMULARIO)
                }

                var pagoSeleccionado by remember {
                    mutableStateOf<Pagos?>(null)
                }

                when (pantalla) {
                    Pantalla.FORMULARIO -> {
                        FormularioScreen(
                            onRegistroExitoso = {
                                pantalla = Pantalla.PERFIL
                            },
                            onVolverLogin = {
                                pantalla = Pantalla.LOGIN
                            }

                        )
                    }

                    Pantalla.LOGIN -> {
                        LoginScreen(
                            onIniciarSesion = {
                                pantalla = Pantalla.PERFIL
                            },
                            onCrearCuenta = {
                                pantalla = Pantalla.FORMULARIO
                            },
                            onRecuperarContrasenia = {
                                pantalla = Pantalla.RECUPERAR_CONTRASENIA
                            }
                        )
                    }

                    Pantalla.PERFIL -> {
                        PerfilScreen(
                            onCompraClick = { pago ->
                                pagoSeleccionado = pago
                                pantalla = Pantalla.PAGOS
                            },
                            onEditarPerfil = {
                                pantalla = Pantalla.EDITAR_PERFIL
                            }
                        )
                    }


                    Pantalla.EDITAR_PERFIL -> {
                        EditarPerfilScreen(
                            onVolver = {
                                pantalla = Pantalla.PERFIL
                            }
                        )
                    }

                    Pantalla.PAGOS -> {
                        PagosScreen(
                            pagoSeleccionado = pagoSeleccionado
                        )
                    }

                    Pantalla.RECUPERAR_CONTRASENIA -> {
                        RecuperarContraseniaScreen()
                    }
                }
            }
        }
    }
}
