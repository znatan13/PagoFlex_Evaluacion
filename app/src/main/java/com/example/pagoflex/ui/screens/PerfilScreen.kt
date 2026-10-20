package com.example.pagoflex.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pagoflex.model.PerfilUiState
import com.example.pagoflex.viewmodel.PerfilViewModel

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

    ) {

    }
}