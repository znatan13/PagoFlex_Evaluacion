package com.example.pagoflex.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text

@Composable
fun PerfilScreen(
    viewModel: PerfilViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PerfilContent(
        uiState = uiState,
        onPagar = viewModel::onPagar
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilContent(
    uiState: PerfilUiState,
    onPagar: () -> Unit
) {
    val perfil = uiState.perfil

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Mi perfil") })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onPagar,
                icon = {
                    Icon(
                        painter = painterResource(if (uiState.pagaste) R.drawable.ic_estado_correcto else R.drawable.ic_estado_incorrecto),
                        contentDescription = null
                    )
                },
                text = {
                    Text(if (uiState.pagaste) "Pagado" else "Pendiente")
                },
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(Dimens.EspacioPequeno))
            Text(text = perfil.nombre, style = MaterialTheme.typography.titleLarge)
            Column(
                modifier = Modifier.padding(Dimens.Pantalla),
                verticalArrangement = Arrangement.spacedBy(Dimens.EspacioMediano)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(Dimens.EspacioMediano)) {
                    Card(modifier = Modifier.weight(1f)) {
                        Column(Modifier.padding(16.dp)) {
                            Text("Suscripciones")
                            Text(perfil.suscripciones.toString())
                        }

                        Card(modifier = Modifier.weight(1f)) {
                            Column(Modifier.padding(16.dp)) {
                                Text("Cuentas pagadas")
                                Text(perfil.pagadas.toString())
                            }
                        }
                        Spacer(Modifier.height(72.dp))
                    }
                }
            }
        }
    }
}