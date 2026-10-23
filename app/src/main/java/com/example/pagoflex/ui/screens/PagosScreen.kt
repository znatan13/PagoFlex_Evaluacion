package com.example.pagoflex.ui.screens

import com.example.pagoflex.ui.styles.EstiloPagos
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pagoflex.viewmodel.PagosViewModel

@Composable
fun PagosScreen(
    viewModel: PagosViewModel = viewModel()
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Mis Pagos",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Administra Tus Proximos Compromisos",
                style = MaterialTheme.typography.bodyLarge
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Proximos Pagos",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text("Aqui aparecera tu lista de pagos.")
                }
            }

            Button(
                onClick = {
                    // aqui puedes abrir el formulario de nuevo pago
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Compromiso")
            }
        }
    }
}