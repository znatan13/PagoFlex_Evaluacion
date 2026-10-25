package com.example.pagoflex.ui.screens
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pagoflex.R
import com.example.pagoflex.model.Pagos
import com.example.pagoflex.model.PagosUrgentes
import com.example.pagoflex.ui.components.BotonPrincipal
import com.example.pagoflex.ui.components.BotonSecundario
import com.example.pagoflex.ui.styles.EstiloPagos
import com.example.pagoflex.ui.themes.Dimens
import com.example.pagoflex.ui.themes.PagoFlexTheme
import com.example.pagoflex.viewmodel.PagosViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PagosScreen(
    viewModel: PagosViewModel = viewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PagosContent(
        uiState = uiState,
        onAlternarPago = viewModel::onAlternarPago,
        onPagado = viewModel::onPagado
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagosContent(
    uiState: PagosUrgentes,
    onAlternarPago: () -> Unit,
    onPagado: () -> Unit
) {
    val pago = uiState.pagos

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.titulo_pago))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(Dimens.Pantalla),
            verticalArrangement = Arrangement.spacedBy(Dimens.EspacioMediano)
        ) {

            Text(
                text = stringResource(R.string.desc_pago),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(R.string.valor_pago),
                style = EstiloPagos.titulo
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = EstiloPagos.tarjeta,
                elevation = CardDefaults.cardElevation(
                    defaultElevation = Dimens.elevacionTarjeta
                ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Column(
                    modifier = Modifier.padding(Dimens.EspacioMediano),
                    verticalArrangement = Arrangement.spacedBy(Dimens.EspacioPequeno)
                ) {
                    Text(
                        text = pago.empresa,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Monto: $${pago.monto}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = "Vencimiento: ${pago.vencimiento}",
                        style = EstiloPagos.texto
                    )

                    Text(
                        text = if (pago.estado) {
                            "Estado: Pagado"
                        } else {
                            "Estado: Pendiente"
                        },
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (pago.estado) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        },
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = if (uiState.urgente) {
                            "Urgente"
                        } else {
                            "No Urgente"
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            BotonPrincipal(
                texto = if (pago.estado) {
                    "Pago realizado"
                } else {
                    "Marcar como pagado"
                },
                onClick = onPagado,
                habilitado = !pago.estado,
                modifier = Modifier.fillMaxWidth()
            )

            BotonSecundario(
                texto = if (uiState.urgente) {
                    "Volver no Urgente"
                } else {
                    "Volver urgente"
                },
                onClick = onAlternarPago,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Historial de pagos",
                style = EstiloPagos.titulo
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = EstiloPagos.tarjeta
            ) {
                Column(
                    modifier = Modifier.padding(Dimens.EspacioMediano),
                    verticalArrangement = Arrangement.spacedBy(Dimens.EspacioMediano)
                ) {
                    if (pago.historial.isEmpty()) {
                        Text(
                            text = "Pague por primera vez!",
                            style = EstiloPagos.texto
                        )
                    } else {
                        pago.historial.forEachIndexed { index, fecha ->
                            Text(
                                text = "Pago ${index + 1}: $fecha",
                                style = EstiloPagos.texto
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimens.EspacioGrande))
        }
    }
}

@Preview(showBackground = true, name = "Pago Inconcluso no urgente")
@Composable
private fun PagosScreenPreviewNoUrgente() {
    PagoFlexTheme {
        PagosContent(
            uiState = PagosUrgentes(
                pagos = Pagos(
                    empresa = "Clavistel",
                    monto = 15000,
                    vencimiento = "17/11/2026",
                    estado = false,
                    historial = listOf("17/10/2026", "17/09/2026")
                ),
                urgente = false
            ),
            onAlternarPago = {},
            onPagado = {}
        )
    }
}

@Preview(showBackground = true, name = "Pago Inconcluso urgente")
@Composable
private fun PagosScreenPreviewUrgente() {
    PagoFlexTheme {
        PagosContent(
            uiState = PagosUrgentes(
                pagos = Pagos(
                    empresa = "Clavistel",
                    monto = 15000,
                    vencimiento = "30/10/2026",
                    estado = false,
                    historial = listOf("17/10/2026", "17/09/2026")
                ),
                urgente = true
            ),
            onAlternarPago = {},
            onPagado = {}
        )
    }
}

@Preview(showBackground = true, name = "Pago concluido")
@Composable
private fun PagosScreenPreviewPagado() {
    PagoFlexTheme {
        PagosContent(
            uiState = PagosUrgentes(
                pagos = Pagos(
                    empresa = "Clavistel",
                    monto = 15000,
                    vencimiento = "30/10/2026",
                    estado = true,
                    historial = listOf("17/10/2026", "17/09/2026", "hoy")
                ),
                urgente = false
            ),
            onAlternarPago = {},
            onPagado = {}
        )
    }
}
@Preview(showBackground = true, name = "Primer pago")
@Composable
private fun PagosScreenPreviewPrimero() {
    PagoFlexTheme {
        PagosContent(
            uiState = PagosUrgentes(
                pagos = Pagos(
                    empresa = "Clavistel",
                    monto = 15000,
                    vencimiento = "30/10/2026",
                    estado = false,
                    historial = listOf()
                ),
                urgente = false
            ),
            onAlternarPago = {},
            onPagado = {}
        )
    }
}




