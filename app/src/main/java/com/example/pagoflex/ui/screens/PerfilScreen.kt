package com.example.pagoflex.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pagoflex.R
import com.example.pagoflex.model.PerfilUiState
import com.example.pagoflex.ui.themes.Dimens
import com.example.pagoflex.viewmodel.PerfilViewModel
import androidx.compose.runtime.getValue
import androidx.compose.material3.Card
import androidx.compose.ui.tooling.preview.Preview
import com.example.pagoflex.model.Perfil
import com.example.pagoflex.ui.components.Avatar
import com.example.pagoflex.ui.themes.PagoFlexTheme
@Composable
fun PerfilScreen(
    viewModel: PerfilViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PerfilContent(
        uiState = uiState,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilContent(
    uiState: PerfilUiState,
) {
    val perfil = uiState.perfil

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text(stringResource(R.string.titulo_perfil)) })
        },

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EncabezadoPerfil()

            Spacer(Modifier.height(Dimens.EspacioPequeno))
            Text(text = perfil.nombre, style = MaterialTheme.typography.titleLarge)
            Column(
                modifier = Modifier.padding(Dimens.Pantalla),
                verticalArrangement = Arrangement.spacedBy(Dimens.EspacioMediano)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(Dimens.EspacioPequeno))
                {
                    Card(modifier = Modifier.weight(1f)) {
                        Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Suscripciones")
                            Text( perfil.suscripciones.toString())
                        }

                        Spacer(Modifier.height(5.dp))
                    }


                    Card(modifier = Modifier.weight(1f)) {
                        Column(Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("Cuentas pagadas")
                            Text(perfil.pagadas.toString())
                        }
                        Spacer(Modifier.height(5.dp))
                    }
                }
            }
        }
    }
}


@Composable
private fun EncabezadoPerfil() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.altoPortada + Dimens.tamanoAvatar / 2)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_banner),
            contentDescription = stringResource(R.string.cd_portada),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.altoPortada)
                .align(Alignment.TopCenter)
        )
        Avatar(
            imagen = R.drawable.ic_avatar,
            descripcion = stringResource(R.string.cd_avatar),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

private val perfilPreview = Perfil(
    nombre = "Camila Rojas",
    correo = "camilo.ro@gmail.com",
    pagadas = 2,
    suscripciones = 5
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PerfilPreview() {
    PagoFlexTheme {
        PerfilContent(
            uiState = PerfilUiState(perfil = perfilPreview),)
    }
}