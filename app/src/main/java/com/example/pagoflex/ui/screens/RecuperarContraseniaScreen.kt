package com.example.pagoflex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.example.pagoflex.R
import com.example.pagoflex.ui.components.BotonPrincipal
import com.example.pagoflex.ui.components.CampoCorreo
import com.example.pagoflex.ui.components.ImagenEncabezado
import com.example.pagoflex.ui.components.TarjetaSeccion
import com.example.pagoflex.ui.themes.Dimens
import androidx.compose.material3.ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarContraseniaScreen() {
    var correo by rememberSaveable { mutableStateOf("") }
    var mensaje by rememberSaveable { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recuperar contraseña") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ImagenEncabezado(
                imagen = R.drawable.banner_pago,
                descripcion = "Banner de recuperación",
                titulo = "Recupera tu cuenta",
                subtitulo = "Te ayudaremos a restablecer tu contraseña"
            )

            Column(
                modifier = Modifier.padding(Dimens.Pantalla),
                verticalArrangement = Arrangement.spacedBy(Dimens.EspacioMediano)
            ) {
                TarjetaSeccion(valor = "Correo de la cuenta") {
                    CampoCorreo(
                        valor = correo,
                        onValorCambia = {
                            correo = it
                            mensaje = null
                        },
                        etiqueta = "Correo electrónico",
                        icono = R.drawable.ic_correo
                    )
                }

                BotonPrincipal(
                    texto = "Enviar recuperación",
                    onClick = {
                        mensaje = if (correo.contains("@") && correo.contains(".")) {
                            "Se ha enviado la recuperación a tu correo."
                        } else {
                            "Ingrese un correo válido"
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                mensaje?.let {
                    Text(
                        text = it,
                        color = if (it.startsWith("Se ha enviado")) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(Modifier.height(Dimens.EspacioGrande))
            }
        }
    }
}
