package com.example.pagoflex.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pagoflex.model.FormularioUiState
import com.example.pagoflex.viewmodel.FormularioViewModel
import com.example.pagoflex.viewmodel.PerfilViewModel

@Composable
fun FormularioScreen(
    viewModel: FormularioViewModel = viewModel(){}
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
    Scaffold(

    ) {

    }
}