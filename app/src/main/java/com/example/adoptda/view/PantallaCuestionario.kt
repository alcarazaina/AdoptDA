package com.example.adoptda.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.adoptda.R
import com.example.adoptda.model.*
import com.example.adoptda.view.ui.theme.Pink
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCuestionario(navController: NavController) {
    val context = LocalContext.current
    val baseDatos = remember { BaseDatos(context) }
    var usuario by remember { mutableStateOf<Usuario?>(null) }

    LaunchedEffect(key1 = true) {
        usuario = baseDatos.obtenerUsuario()
    }

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var dni by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var tiempoEnCasa by remember { mutableStateOf("") }
    var tieneMasAnimales by remember { mutableStateOf(false) }
    var haTenidoMasAnimales by remember { mutableStateOf(false) }
    var asumeGastosVeterinarios by remember { mutableStateOf(false) }
    var tiempoCalidad by remember { mutableStateOf(false) }
    var pisoOCasa by remember { mutableStateOf(false) }

    var isNombreValid by remember { mutableStateOf(false) }
    var isApellidoValid by remember { mutableStateOf(false) }
    var isDniValid by remember { mutableStateOf(false) }
    var isCorreoValid by remember { mutableStateOf(false) }

    var isSubmitting by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    val backgroundImage = ImageBitmap.imageResource(id = R.drawable.fondo)

    val isFormValid = isNombreValid && isApellidoValid && isDniValid && isCorreoValid

    LaunchedEffect(usuario) {
        usuario?.let { user ->
            nombre = user.nombre
            apellido = user.apellido
            dni = user.dni
            correo = user.correo
            tiempoEnCasa = user.tiempoEnCasa.toString()
            tieneMasAnimales = user.masAnimales
            haTenidoMasAnimales = user.experienciaPrevia
            asumeGastosVeterinarios = user.gastosVeterinario
            tiempoCalidad = user.tiempoCalidad
            pisoOCasa = user.pisoOCasa

            // Set initial validation states
            isNombreValid = user.nombre.isNotBlank()
            isApellidoValid = user.apellido.isNotBlank()
            isDniValid = user.dni.isNotBlank()
            isCorreoValid = user.correo.isNotBlank()
        }
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
                            text = stringResource(R.string.cuestionario),
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
                        PreguntaTextField(
                            "Introduce tu nombre",
                            nombre,
                            onNombreChange = { nombre = it },
                            onValidationChange = { isNombreValid = it }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaTextField(
                            "Introduce tu apellido",
                            apellido,
                            onNombreChange = { apellido = it },
                            onValidationChange = { isApellidoValid = it }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaTextField(
                            "Introduce tu DNI",
                            dni,
                            onNombreChange = { dni = it },
                            onValidationChange = { isDniValid = it }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaTextField(
                            "Introduce tu correo electronico",
                            correo,
                            onNombreChange = { correo = it },
                            onValidationChange = { isCorreoValid = it }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        PreguntaTiempo(
                            "¿Cuánto tiempo pasa en casa?",
                            tiempoEnCasa,
                            onHoraSelected = { tiempoEnCasa = it },
                        )
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
                                isSubmitting = true
                                val updatedUsuario = usuario?.copy(
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
                                ) ?: Usuario(
                                    idUsuario = 0,
                                    nombre = nombre,
                                    apellido = apellido,
                                    dni = dni,
                                    correo = correo,
                                    tiempoEnCasa = tiempoEnCasa.toIntOrNull() ?: 0,
                                    masAnimales = tieneMasAnimales,
                                    experienciaPrevia = haTenidoMasAnimales,
                                    gastosVeterinario = asumeGastosVeterinarios,
                                    tiempoCalidad = tiempoCalidad,
                                    pisoOCasa = pisoOCasa,
                                    animalesSolicitados = listOf()
                                )
                                scope.launch {
                                    // Actualizar usuario en la base de datos
                                    val success = baseDatos.actualizarUsuarioCompleto(updatedUsuario)
                                    delay(2000)
                                    isSubmitting = false

                                    if (success) {
                                        Toast.makeText(context, "Usuario actualizado correctamente", Toast.LENGTH_LONG).show()
                                    } else {
                                        Toast.makeText(context, "Error al actualizar el usuario", Toast.LENGTH_LONG).show()
                                    }

                                    delay(1000)
                                    navController.navigate("perfilesUsuario")
                                }
                            },
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(0.8f),
                            colors = ButtonDefaults.buttonColors(Pink),
                            enabled = isFormValid && !isSubmitting
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
            floatingActionButtonPosition = FabPosition.Start,
            containerColor = Color.Transparent
        )

        if (isSubmitting) {
            Dialog(onDismissRequest = {}) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Guardando datos...",
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        LinearProgressIndicator(
                            progress = progress,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = Pink
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(isSubmitting) {
        if (isSubmitting) {
            progress = 0f
            while (progress < 1f) {
                delay(30)
                progress += 0.01f
            }
        }
    }
}