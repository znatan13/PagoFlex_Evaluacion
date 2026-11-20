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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pagoflex.viewmodel.PerfilViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfilScreen(
    onVolver: () -> Unit,
    viewModel: PerfilViewModel = viewModel()
) {
    val perfil = viewModel.uiState.collectAsStateWithLifecycle().value.perfil

    var nombre by remember { mutableStateOf(perfil.nombre) }
    var correo by remember { mutableStateOf(perfil.correo) }
    var contrasenia by remember { mutableStateOf(viewModel.obtenerContrasenia()) }
    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorCorreo by remember { mutableStateOf<String?>(null) }
    var errorContrasenia by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Cambiar datos") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    nombre = it
                    errorNombre = null
                },
                label = { Text("Nombre") },
                isError = errorNombre != null,
                supportingText = { errorNombre?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = correo,
                onValueChange = {
                    correo = it
                    errorCorreo = null
                },
                label = { Text("Correo") },
                isError = errorCorreo != null,
                supportingText = { errorCorreo?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = contrasenia,
                onValueChange = {
                    contrasenia = it
                    errorContrasenia = null
                },
                label = { Text("Contraseña") },
                isError = errorContrasenia != null,
                supportingText = { errorContrasenia?.let { Text(it) } },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un símbolo, y no puede contener tu nombre.",
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(Modifier.height(4.dp))

            Button(
                onClick = {
                    errorNombre = when {
                        nombre.isBlank() -> "El nombre es obligatorio"
                        nombre.any { it.isDigit() } -> "El nombre no puede contener números"
                        else -> null
                    }
                    errorCorreo = if (!correo.contains("@") || !correo.contains(".")) {
                        "Ingrese un correo válido"
                    } else null
                    val nombreEnMinusculas = nombre.lowercase()
                    val contraseniaEnMinusculas = contrasenia.lowercase()
                    errorContrasenia = when {
                        contrasenia.length < 8 -> "La contraseña debe tener al menos 8 caracteres"
                        !contrasenia.any { it.isUpperCase() } -> "Debe contener al menos una mayúscula"
                        !contrasenia.any { it.isLowerCase() } -> "Debe contener al menos una minúscula"
                        !contrasenia.any { it.isDigit() } -> "Debe contener al menos un número"
                        !contrasenia.any { !it.isLetterOrDigit() } -> "Debe contener al menos un símbolo"
                        nombreEnMinusculas.isNotBlank() && contraseniaEnMinusculas.contains(nombreEnMinusculas) -> "La contraseña no puede contener tu nombre"
                        else -> null
                    }

                    if (errorNombre == null && errorCorreo == null && errorContrasenia == null) {
                        viewModel.actualizarPerfil(nombre, correo, contrasenia)
                        onVolver()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar cambios")
            }

            Button(
                onClick = onVolver,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}
