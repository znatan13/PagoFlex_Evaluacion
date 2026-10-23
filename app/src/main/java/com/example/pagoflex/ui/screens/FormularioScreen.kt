package com.example.pagoflex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pagoflex.R
import com.example.pagoflex.model.FormularioUiState
import com.example.pagoflex.ui.themes.Dimens
import com.example.pagoflex.viewmodel.FormularioViewModel
import androidx.compose.runtime.getValue
import com.example.pagoflex.viewmodel.PerfilViewModel

@Composable
fun FormularioScreen(
    viewModel: FormularioViewModel = viewModel ()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FormularioContent(
        uiState = uiState,
        onNombreCambia = viewModel::onNombreCambia,
        onCorreoCambia = viewModel::onCorreoCambia,
        onContraseniaCambia = viewModel::onContraseniaCambia,
        onAlternarVisibilidad = viewModel::onAlternarVisibilidad,
        onRegistrar = viewModel::onRegistrar,
        onLimpiar = viewModel::onLimpiar
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioContent(
    uiState: FormularioUiState,
    onNombreCambia: (String) -> Unit,
    onCorreoCambia: (String) -> Unit,
    onContraseniaCambia: (String) -> Unit,
    onAlternarVisibilidad: () -> Unit,
    onRegistrar: () -> Unit,
    onLimpiar: () -> Unit
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Crear cuenta") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    )   {
        innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            ImagenEncabezado(
                imagen = R.drawable./* BANNER AQUI */,
                descripcion = Text("Banner de formulario"),
                titulo = Text("Inicia Sesion."),
                subtitulo = Text("Y paga tus cuentas facil.")
            )
        }
        Column(
            modifier = Modifier.padding(Dimens.Pantalla),
            verticalArrangement = Arrangement.spacedBy(Dimens.EspacioMediano)
        ) {
            TarjetaSeccion(titulo = Text("Datos personales")) {
                CampoTexto(
                    valor = uiState.nombre,
                    onValorCambia = onNombreCambia,
                    etiqueta = stringResource("Nombre completo"),
                    icono = R.drawable./*ICONO PERFIL*/,
                    error = uiState.errorNombre
                )
                CampoCorreo(
                    valor = uiState.correo,
                    onValorCambia = onCorreoCambia,
                    etiqueta = stringResource("Nombre completo"),
                    icono = R.drawable./*ICONO CORREO*/,
                    error = uiState.errorCorreo
                )

                CampoContrasenia(
                    valor = uiState.contrasenia,
                    onValorCambia = onContraseniaCambia,
                    etiqueta = stringResource("Nombre completo"),
                    icono = R.drawable./*ICONO CANDADO*/,
                    error = uiState.errorContrasenia
                )
            }
        }
    }

}