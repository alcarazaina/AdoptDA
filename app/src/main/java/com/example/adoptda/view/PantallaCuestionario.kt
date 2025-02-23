package com.example.adoptda.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adoptda.R
import com.example.adoptda.model.PreguntaBoolean
import com.example.adoptda.model.PreguntaTextField
import com.example.adoptda.model.PreguntaTiempo
import com.example.adoptda.model.Usuario
import com.example.adoptda.view.ui.theme.Pink

@Composable
fun PantallaCuestionario(navController: NavController, usuario: Usuario) {
    var nombre by remember { mutableStateOf(usuario.nombre) }
    var apellido by remember { mutableStateOf(usuario.apellido) }
    var dni by remember { mutableStateOf(usuario.dni) }
    var correo by remember { mutableStateOf(usuario.correo) }
    var tiempoEnCasa by remember { mutableStateOf(usuario.tiempoEnCasa.toString()) }
    var tieneMasAnimales by remember { mutableStateOf(usuario.experienciaPrevia) }
    var haTenidoMasAnimales by remember { mutableStateOf(usuario.masAnimales) }
    var asumeGastosVeterinarios by remember { mutableStateOf(usuario.gastosVeterinario) }
    var tiempoCalidad by remember { mutableStateOf(usuario.tiempoCalidad) }
    var pisoOCasa by remember { mutableStateOf(usuario.pisoOCasa) }

    val backgroundImage = ImageBitmap.imageResource(id = R.drawable.fondo)

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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    Text(
                        stringResource(R.string.cuestionario),
                        textAlign = TextAlign.Center,
                    )
                }
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        PreguntaTextField("Introduce tu nombre", nombre) { nombre = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaTextField("Introduce tu apellido", apellido) { apellido = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaTextField("Introduce tu DNI", dni) { dni = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaTextField("Introduce tu correo electronico", correo) { correo = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaTiempo(
                            "¿Cuánto tiempo pasa en casa?",
                            tiempoEnCasa
                        ) { tiempoEnCasa = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaBoolean(
                            "¿Tiene más mascotas?",
                            tieneMasAnimales
                        ) { tieneMasAnimales = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaBoolean(
                            "¿Ha tenido mascotas antes?",
                            haTenidoMasAnimales
                        ) { haTenidoMasAnimales = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaBoolean(
                            "¿Está dispuesto a asumir los gastos veterinarios?",
                            asumeGastosVeterinarios
                        ) { asumeGastosVeterinarios = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaBoolean(
                            "¿Tiene tiempo para paseos y juegos?",
                            tiempoCalidad
                        ) { tiempoCalidad = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaBoolean("¿Vive en casa o apartamento?", pisoOCasa) {
                            pisoOCasa = it
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                val updatedUsuario = usuario.copy(
                                    nombre = nombre,
                                    apellido = apellido,
                                    dni = dni,
                                    correo = correo,
                                    tiempoEnCasa = tiempoEnCasa.toIntOrNull() ?: 0,
                                    masAnimales = tieneMasAnimales,
                                    experienciaPrevia = haTenidoMasAnimales,
                                    gastosVeterinario = asumeGastosVeterinarios,
                                    tiempoCalidad = tiempoCalidad,
                                    pisoOCasa = pisoOCasa
                                )
                                println("Updated Usuario: $updatedUsuario")
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(0.8f),
                            colors = ButtonDefaults.buttonColors(Pink)
                        ) {
                            Text(stringResource(R.string.aceptar))
                        }
                    }
                }

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
            floatingActionButtonPosition = FabPosition.Start
        )
    }
}

