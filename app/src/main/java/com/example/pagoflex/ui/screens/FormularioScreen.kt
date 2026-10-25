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
import androidx.compose.ui.tooling.preview.Preview
import com.example.pagoflex.ui.components.CampoContrasenia
import com.example.pagoflex.ui.components.CampoCorreo
import com.example.pagoflex.ui.components.CampoTexto
import com.example.pagoflex.ui.components.CasillaConTexto
import com.example.pagoflex.ui.components.ImagenEncabezado
import com.example.pagoflex.ui.components.TarjetaSeccion
import com.example.pagoflex.ui.themes.PagoFlexTheme
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
        onTerminosCambia = viewModel::onTerminosCambia,
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
    onTerminosCambia: (Boolean) -> Unit,
    onRegistrar: () -> Unit,
    onLimpiar: () -> Unit
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(stringResource(aaaaaaa)) },
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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {

            ImagenEncabezado(
                imagen = R.drawable.banner_pago,
                descripcion = "Banner de formulario",
                titulo = "Inicia Sesion.",
                subtitulo = "Y paga tus cuentas facil."
            )
        }
        Column(
            modifier = Modifier.padding(Dimens.Pantalla),
            verticalArrangement = Arrangement.spacedBy(Dimens.EspacioMediano)
        ) {
            TarjetaSeccion (valor = stringResource(R.string.seccion_datos)) {
                CampoTexto(
                    valor = uiState.nombre,
                    onValorCambia = onNombreCambia,
                    etiqueta = stringResource(),
                    icono = R.drawable.ic_persona,
                    error = uiState.errorNombre
                )

                CampoCorreo(
                    valor = uiState.correo,
                    onValorCambia = onCorreoCambia,
                    etiqueta = stringResource(),
                    icono = R.drawable.ic_correo,
                    error = uiState.errorCorreo
                )

                CampoContrasenia(
                    valor = uiState.contrasenia,
                    onValorCambia = onContraseniaCambia,
                    etiqueta = stringResource(),
                    icono = R.drawable.ic_candado,
                    error = uiState.errorContrasenia
                )

            }
            Column {
                CasillaConTexto(
                    texto = stringResource(R.string.opcion_terminos),
                    marcado = uiState.aceptarTerminos,
                    onCambio = onTerminosCambia
                )
                uiState.errorTerminos?.let { mensaje ->
                    Text(
                        text = mensaje,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = Dimens.EspacioPequeno)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FormularioVacioPreview(){
    PagoFlexTheme {
        FormularioContent(
            uiState = FormularioUiState(
                correo = "@gmail.com",
                errorNombre = "El nombre es obligatorio",
                errorCorreo = "Ingrese un correo valido",
                errorContrasenia = "Minimo 6 caracteres",
                errorTerminos = "Debes aceptar los terminos"
            ),
            onNombreCambia = {},
            onCorreoCambia = {},
            onContraseniaCambia = {},
            onAlternarVisibilidad = {},
            onTerminosCambia = {},
            onRegistrar = {},
            onLimpiar = {}

        )
    }
}