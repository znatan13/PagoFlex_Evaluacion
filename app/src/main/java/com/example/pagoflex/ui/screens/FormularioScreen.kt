package com.example.pagoflex.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.pagoflex.ui.components.BotonPrincipal
import com.example.pagoflex.ui.components.BotonSecundario
import com.example.pagoflex.ui.components.CampoContrasenia
import com.example.pagoflex.ui.components.CampoCorreo
import com.example.pagoflex.ui.components.CampoTexto
import com.example.pagoflex.ui.components.CasillaConTexto
import com.example.pagoflex.ui.components.ImagenEncabezado
import com.example.pagoflex.ui.components.TarjetaSeccion
import com.example.pagoflex.ui.themes.Exito
import com.example.pagoflex.ui.themes.Fondo
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
                title = { Text(stringResource(R.string.titulo_formulario)) },
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
                    etiqueta = stringResource(R.string.campo_nombre),
                    icono = R.drawable.ic_persona,
                    error = uiState.errorNombre
                )

                CampoCorreo(
                    valor = uiState.correo,
                    onValorCambia = onCorreoCambia,
                    etiqueta = stringResource(R.string.campo_correo),
                    icono = R.drawable.ic_correo,
                    error = uiState.errorCorreo
                )

                CampoContrasenia(
                    valor = uiState.contrasenia,
                    onValorCambia = onContraseniaCambia,
                    etiqueta = stringResource(R.string.campo_contrasenia),
                    visible = uiState.contraseniaVisible,
                    onAlternarVisible = onAlternarVisibilidad,
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

            Row(horizontalArrangement = Arrangement.spacedBy(Dimens.EspacioMediano)) {
                BotonSecundario(
                    texto = stringResource(R.string.btn_limbiar),
                    onClick = onLimpiar,
                    modifier = Modifier.weight(1f)
                )
                BotonPrincipal(
                        texto = stringResource(R.string.btn_registro),
                        onClick = onRegistrar,
                        icono = R.drawable.ic_estado_correcto,
                        modifier = Modifier.weight(1f)
                )
            }
            if (uiState.registroExitoso) {
                MensajeExito(nombre = uiState.nombre)
            }

            Spacer(Modifier.height(Dimens.EspacioGrande))
        }
    }
}


@Composable
private fun MensajeExito(nombre: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Fondo, contentColor = Exito)
    ) {
        Row(
            modifier = Modifier.padding(Dimens.EspacioMediano),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_estado_correcto),
                contentDescription = null,
                tint = Exito,
                modifier = Modifier.size(Dimens.tamanoIcono)
            )
            Text(
                text = stringResource(R.string.registro_exitoso, nombre),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = Dimens.EspacioPequeno)
            )
        }
    }
}

@Preview(showBackground = true)
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

@Preview(showBackground = true, name = "Con errores")
@Composable
private fun FormularioConErroresPreview() {
    PagoFlexTheme {
        FormularioContent(
            uiState = FormularioUiState(
                correo = "ana@",
                errorNombre = "El nombre es obligatorio",
                errorCorreo = "Ingresa un correo válido",
                errorContrasenia = "Mínimo 6 caracteres",
                errorTerminos = "Debes aceptar los términos"
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