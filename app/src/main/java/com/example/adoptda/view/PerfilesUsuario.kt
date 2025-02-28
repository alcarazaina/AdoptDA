package com.example.adoptda.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoptda.R
import com.example.adoptda.model.BaseDatos
import com.example.adoptda.model.PreguntaBoolean
import com.example.adoptda.model.PreguntaTextField
import com.example.adoptda.model.PreguntaTiempo
import com.example.adoptda.model.Usuario
import com.example.adoptda.view.ui.theme.Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilesUsuario(navController: NavController) {
    val backgroundImage = ImageBitmap.imageResource(id = R.drawable.fondo)
    val context = LocalContext.current
    val baseDatos = remember { BaseDatos(context) }

    // Estado para la lista de usuarios
    var usuarios by remember { mutableStateOf(listOf<Usuario>()) }
    // Estado para mostrar diálogo de confirmación de eliminación
    var usuarioAEliminar by remember { mutableStateOf<Usuario?>(null) }
    // Estado para mostrar detalles de un usuario
    var usuarioSeleccionado by remember { mutableStateOf<Usuario?>(null) }

    // Efecto para cargar los usuarios de la base de datos
    LaunchedEffect(key1 = true) {
        usuarios = baseDatos.obtenerTodosUsuarios()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val imageWidth = backgroundImage.width.toFloat()
            val imageHeight = backgroundImage.height.toFloat()

            for (x in 0..(size.width / imageWidth).toInt()) {
                for (y in 0..(size.height / imageHeight).toInt()) {
                    drawImage(
                        image = backgroundImage,
                        topLeft = androidx.compose.ui.geometry.Offset(
                            x * imageWidth,
                            y * imageHeight
                        )
                    )
                }
            }
        }

        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            "Perfiles de Usuario",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Pink
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.popBackStack() },
                    containerColor = Pink,
                    contentColor = Color.White,
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                }
            },
            floatingActionButtonPosition = FabPosition.Start,
            containerColor = Color.Transparent
        ) { innerPadding ->
            if (usuarios.isEmpty()) {
                // Mostrar mensaje cuando no hay usuarios
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Text(
                            "No hay usuarios registrados",
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }
            } else {
                // Mostrar lista de usuarios
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                ) {
                    items(usuarios) { usuario ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            onClick = {
                                usuarioSeleccionado = usuario
                            }
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text(
                                        "${usuario.nombre} ${usuario.apellido}",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                    Text(
                                        "DNI: ${usuario.dni}",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                    Text(
                                        "Email: ${usuario.correo}",
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        usuarioAEliminar = usuario
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Eliminar",
                                        tint = Pink
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Diálogo de confirmación para eliminar usuario
        if (usuarioAEliminar != null) {
            AlertDialog(
                onDismissRequest = { usuarioAEliminar = null },
                title = { Text("Confirmar eliminación") },
                text = { Text("¿Estás seguro de que deseas eliminar a ${usuarioAEliminar?.nombre} ${usuarioAEliminar?.apellido}?") },
                confirmButton = {
                    Button(
                        onClick = {
                            usuarioAEliminar?.let { usuario ->
                                baseDatos.eliminarUsuario(usuario.idUsuario)
                                // Actualizar la lista de usuarios
                                usuarios = baseDatos.obtenerTodosUsuarios()
                                usuarioAEliminar = null
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Pink)
                    ) {
                        Text("Eliminar")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { usuarioAEliminar = null }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }

        // Diálogo con detalles completos del usuario
        if (usuarioSeleccionado != null) {
            AlertDialog(
                onDismissRequest = { usuarioSeleccionado = null },
                title = {
                    Text(
                        "Detalles del usuario",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                text = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        DetalleUsuarioItem("Nombre", "${usuarioSeleccionado?.nombre} ${usuarioSeleccionado?.apellido}")
                        DetalleUsuarioItem("DNI", usuarioSeleccionado?.dni ?: "")
                        DetalleUsuarioItem("Correo", usuarioSeleccionado?.correo ?: "")
                        DetalleUsuarioItem("Tiempo en casa", "${usuarioSeleccionado?.tiempoEnCasa} horas")
                        DetalleUsuarioItem("Tiene más mascotas", if (usuarioSeleccionado?.masAnimales == true) "Sí" else "No")
                        DetalleUsuarioItem("Ha tenido mascotas antes", if (usuarioSeleccionado?.experienciaPrevia == true) "Sí" else "No")
                        DetalleUsuarioItem("Asume gastos veterinarios", if (usuarioSeleccionado?.gastosVeterinario == true) "Sí" else "No")
                        DetalleUsuarioItem("Tiempo para cuidados", if (usuarioSeleccionado?.tiempoCalidad == true) "Sí" else "No")
                        DetalleUsuarioItem("Tipo de vivienda", if (usuarioSeleccionado?.pisoOCasa == true) "Casa" else "Apartamento")
                        DetalleUsuarioItem("Animales solicitados", usuarioSeleccionado?.animalesSolicitados?.joinToString(", ") ?: "Ninguno")
                    }
                },
                confirmButton = {
                    Button(
                        onClick = { usuarioSeleccionado = null },
                        colors = ButtonDefaults.buttonColors(containerColor = Pink)
                    ) {
                        Text("Cerrar")
                    }
                }
            )
        }
    }
}

@Composable
fun DetalleUsuarioItem(etiqueta: String, valor: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = etiqueta,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Text(
            text = valor,
            fontSize = 16.sp
        )
        Divider(
            modifier = Modifier.padding(top = 4.dp),
            color = Color.LightGray
        )
    }
}