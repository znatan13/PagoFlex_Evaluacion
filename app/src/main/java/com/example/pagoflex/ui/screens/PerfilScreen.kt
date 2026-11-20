package com.example.pagoflex.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.border
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pagoflex.R
import com.example.pagoflex.model.Pagos
import com.example.pagoflex.model.Perfil
import com.example.pagoflex.model.PerfilUiState
import com.example.pagoflex.ui.components.Avatar
import com.example.pagoflex.ui.themes.Dimens
import com.example.pagoflex.ui.themes.PagoFlexTheme
import com.example.pagoflex.viewmodel.PerfilViewModel
import androidx.compose.material3.Card
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PerfilScreen(
    onCompraClick: (Pagos) -> Unit = {},
    onEditarPerfil: () -> Unit = {},
    viewModel: PerfilViewModel = viewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = androidx.compose.ui.platform.LocalContext.current

    var imagenPerfil by remember { mutableStateOf<Bitmap?>(null) }
    var mostrarOpciones by remember { mutableStateOf(false) }

    val galeriaLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            context.contentResolver.openInputStream(it)?.use { stream ->
                imagenPerfil = BitmapFactory.decodeStream(stream)
            }
        }
    }

    val camaraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        if (bitmap != null) {
            imagenPerfil = bitmap
        }
    }

    val permisoCamaraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { permitido ->
        if (permitido) {
            camaraLauncher.launch(null)
        }
    }

    PerfilContent(
        uiState = uiState,
        imagenPerfil = imagenPerfil,
        onCambiarFoto = { mostrarOpciones = true },
        onCompraClick = onCompraClick,
        onEditarPerfil = onEditarPerfil
    )

    if (mostrarOpciones) {
        AlertDialog(
            onDismissRequest = { mostrarOpciones = false },
            title = { Text("Cambiar foto de perfil") },
            text = { Text("Selecciona de dónde quieres obtener la nueva foto.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarOpciones = false
                        if (ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            camaraLauncher.launch(null)
                        } else {
                            permisoCamaraLauncher.launch(Manifest.permission.CAMERA)
                        }
                    }
                ) {
                    Text("Cámara")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarOpciones = false
                        galeriaLauncher.launch("image/*")
                    }
                ) {
                    Text("Galería")
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilContent(
    uiState: PerfilUiState,
    imagenPerfil: Bitmap? = null,
    onCambiarFoto: () -> Unit = {},
    onCompraClick: (Pagos) -> Unit = {},
    onEditarPerfil: () -> Unit = {}
) {
    val perfil = uiState.perfil

    val compras = listOf(
        Pagos(
            empresa = "Clavistel",
            monto = 15000,
            vencimiento = "17/11/2026",
            estado = false,
            historial = listOf("17/10/2026", "17/09/2026"),
            descripcion = "Compañia telefonica",
            metodoPago = "Tarjeta de débito",
            categoria = "Servicios"
        ),
        Pagos(
            empresa = "Musifi",
            monto = 8990,
            vencimiento = "20/11/2026",
            estado = true,
            historial = listOf("20/10/2026", "20/09/2026"),
            descripcion = "Aplicacion de musica",
            metodoPago = "Tarjeta de crédito",
            categoria = "Suscripciones"
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.titulo_perfil))
                },
                actions = {
                    androidx.compose.material3.IconButton(
                        onClick = onEditarPerfil
                    ) {
                        Text("⚙", style = MaterialTheme.typography.titleLarge)
                    }
                }
            )
        },

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EncabezadoPerfil(
                imagenPerfil = imagenPerfil,
                onCambiarFoto = onCambiarFoto
            )

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
                            Text(perfil.suscripciones.toString())
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

                Text(
                    text = "Servicios",
                    style = MaterialTheme.typography.titleLarge
                )

                compras.forEach { compra ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onCompraClick(compra) }
                    ) {
                        Column(
                            modifier = Modifier.padding(Dimens.EspacioMediano),
                            verticalArrangement = Arrangement.spacedBy(Dimens.EspacioPequeno)
                        ) {
                            Text(
                                text = compra.empresa,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Monto: $${compra.monto}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = if (compra.estado) "Pagado" else "Pendiente",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EncabezadoPerfil(
    imagenPerfil: Bitmap?,
    onCambiarFoto: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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

            if (imagenPerfil != null) {
                Image(
                    bitmap = imagenPerfil.asImageBitmap(),
                    contentDescription = stringResource(R.string.cd_avatar),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(Dimens.tamanoAvatar)
                        .clip(androidx.compose.foundation.shape.CircleShape)
                        .border(
                            Dimens.bordeAvatar,
                            MaterialTheme.colorScheme.surface,
                            androidx.compose.foundation.shape.CircleShape
                        )
                        .align(Alignment.BottomCenter)
                )
            } else {
                Avatar(
                    imagen = R.drawable.ic_avatar,
                    descripcion = stringResource(R.string.cd_avatar),
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }

        Button(onClick = onCambiarFoto) {
            Text("Cambiar foto")
        }
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
            uiState = PerfilUiState(perfil = perfilPreview),
        )
    }
}
