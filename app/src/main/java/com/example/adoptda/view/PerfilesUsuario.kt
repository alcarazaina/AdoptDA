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
import androidx.compose.material.icons.filled.Edit
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
import com.example.adoptda.model.GatoRepository
import com.example.adoptda.model.PerroRepository
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

    var usuario by remember { mutableStateOf<Usuario?>(null) }
    var mostrarDialogoConfirmacion by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        usuario = baseDatos.obtenerUsuario()
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
                            "Perfil de Usuario",
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
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 32.dp, end = 32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FloatingActionButton(
                        onClick = { navController.navigate("menu") },
                        containerColor = Pink,
                        contentColor = Color.White,
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                    FloatingActionButton(
                        onClick = { navController.navigate("cuestionario") },
                        containerColor = Pink,
                        contentColor = Color.White,
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar perfil")
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            containerColor = Color.Transparent
        ) { innerPadding ->
            usuario?.let { user ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                ) {
                    item {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                DetalleUsuarioItem("Nombre", "${user.nombre} ${user.apellido}")
                                DetalleUsuarioItem("DNI", user.dni)
                                DetalleUsuarioItem("Correo", user.correo)
                                DetalleUsuarioItem("Tiempo en casa", "${user.tiempoEnCasa} horas")
                                DetalleUsuarioItem("Tiene más mascotas", if (user.masAnimales) "Sí" else "No")
                                DetalleUsuarioItem("Ha tenido mascotas antes", if (user.experienciaPrevia) "Sí" else "No")
                                DetalleUsuarioItem("Asume gastos veterinarios", if (user.gastosVeterinario) "Sí" else "No")
                                DetalleUsuarioItem("Tiempo para cuidados", if (user.tiempoCalidad) "Sí" else "No")
                                DetalleUsuarioItem("Tipo de vivienda", if (user.pisoOCasa) "Casa" else "Apartamento")
                                DetalleUsuarioItem("Animales solicitados", user.animalesSolicitados.joinToString(", ") { obtenerNombreAnimal(it) })
                            }
                        }
                    }
                    item {
                        Button(
                            onClick = { mostrarDialogoConfirmacion = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Pink)
                        ) {
                            Text("Resetear perfil")
                        }
                    }
                }
            } ?: run {
                Text("No se pudo cargar el perfil del usuario", modifier = Modifier.padding(16.dp))
            }
        }

        if (mostrarDialogoConfirmacion) {
            AlertDialog(
                onDismissRequest = { mostrarDialogoConfirmacion = false },
                title = { Text("Confirmar reseteo") },
                text = { Text("¿Estás seguro de que deseas resetear tu perfil? Se borrarán todos tus datos.") },
                confirmButton = {
                    Button(
                        onClick = {
                            baseDatos.resetearUsuario()
                            usuario = baseDatos.obtenerUsuario()
                            mostrarDialogoConfirmacion = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Pink)
                    ) {
                        Text("Resetear")
                    }
                },
                dismissButton = {
                    Button(onClick = { mostrarDialogoConfirmacion = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

// Actualizar la función obtenerNombreAnimal para interpretar correctamente los IDs con prefijo
fun obtenerNombreAnimal(id: Any): String {
    when (id) {
        is String -> {
            // Si el ID es un string, verificar el prefijo
            if (id.startsWith("g")) {
                // Es un gato
                val gatoId = id.substring(1).toIntOrNull() ?: return "Animal desconocido"
                val gato = GatoRepository.getGatoById(gatoId)
                return if (gato != null) "${gato.nombre} (Gato)" else "Gato desconocido"
            } else if (id.startsWith("p")) {
                // Es un perro
                val perroId = id.substring(1).toIntOrNull() ?: return "Animal desconocido"
                val perro = PerroRepository.getPerroById(perroId)
                return if (perro != null) "${perro.nombre} (Perro)" else "Perro desconocido"
            }
        }
        is Int -> {
            // Para compatibilidad con datos antiguos
            val gato = GatoRepository.getGatoById(id)
            val perro = PerroRepository.getPerroById(id)

            return when {
                gato != null -> "${gato.nombre} (Gato)"
                perro != null -> "${perro.nombre} (Perro)"
                else -> "Animal desconocido"
            }
        }
    }

    return "Animal desconocido"
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
            color = Pink
        )
    }
}

