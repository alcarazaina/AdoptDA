package com.example.adoptda.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.example.adoptda.R
import com.example.adoptda.model.BaseDatos
import com.example.adoptda.model.Gato
import com.example.adoptda.model.GatoRepository
import com.example.adoptda.model.PerroRepository
import com.example.adoptda.model.Usuario
import com.example.adoptda.view.ui.theme.Pink


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaAdopcionPerro(navController: NavController, perroId: Int, usuarioId: Usuario) {
    val perro = PerroRepository.getPerroById(perroId) ?: return
    var mostrarDialogoConfirmacion by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val baseDatos = remember { BaseDatos(context) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = perro.imagen),
                    contentDescription = perro.nombre,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.None
                )
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.TopCenter),
                    shape = RoundedCornerShape(30.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Pink,
                        contentColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = perro.nombre,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${perro.edad} años",
                            fontSize = 24.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = perro.sexo,
                            fontSize = 24.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = perro.provincia,
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = perro.descripcion,
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                if (mostrarDialogoConfirmacion) {
                    AlertDialog(
                        onDismissRequest = { mostrarDialogoConfirmacion = false },
                        title = { Text(if (usuarioId.nombre.isEmpty()) stringResource(R.string.iralcuestionario) else "Confirmar solicitud de adopción") },
                        confirmButton = {
                            Button(
                                onClick = {
                                    if (usuarioId.nombre.isEmpty()) {
                                        navController.navigate("cuestionario/${usuarioId.idUsuario}")
                                    } else {
                                        // Agregar solicitud de adopción
                                        baseDatos.agregarSolicitudAdopcion(usuarioId.idUsuario, )
                                        // Mostrar mensaje de confirmación
                                        Toast.makeText(context, "Solicitud de adopción enviada", Toast.LENGTH_SHORT).show()
                                        navController.popBackStack()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Pink)
                            ) {
                                Text(
                                    if (usuarioId.nombre.isEmpty()) stringResource(R.string.aceptar) else "Enviar solicitud",
                                    style = TextStyle(color = Color.White)
                                )
                            }
                        },
                        dismissButton = {
                            Button(colors = ButtonDefaults.buttonColors(containerColor = Pink),
                                onClick = {
                                    mostrarDialogoConfirmacion = false
                                }
                            ) {
                                Text(
                                    stringResource(R.string.cancelar),
                                    style = TextStyle(color = Color.White)
                                )
                            }
                        },
                        containerColor = MaterialTheme.colorScheme.surface,
                        properties = DialogProperties(dismissOnClickOutside = false),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        },

        floatingActionButton = {
            Row(modifier = Modifier.fillMaxWidth().padding(start = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween) {
                FloatingActionButton(
                    onClick = { navController.popBackStack() },
                    containerColor = Pink,
                    contentColor = Color.White,
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                }
                FloatingActionButton(
                    onClick = { mostrarDialogoConfirmacion = true },
                    containerColor = Pink,
                    contentColor = Color.White,
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Regresar")
                }
            }
        },
    )
}