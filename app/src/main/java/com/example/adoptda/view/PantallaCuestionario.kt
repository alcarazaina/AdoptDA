package com.example.adoptda.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.adoptda.model.Usuario
import com.example.adoptda.view.ui.theme.Pink

@Composable
fun PantallaCuestionario(navController: NavController) {
//    var nombre by remember { mutableStateOf(Usuario) }
//    var apellido by remember { mutableStateOf(Usuario.raza ?: "") }
//    var dni by remember { mutableStateOf(Usuario.peso?.toString() ?: "") }
//    var correo by remember { mutableStateOf(Usuario.peso?.toString() ?: "") }
//    var tieneMasAnimales by remember { mutableStateOf(Usuario.peso?.toString() ?: "") }
//    var haTenidoMasAnimales by remember { mutableStateOf(Usuario.peso?.toString() ?: "") }
//    var tiempoEnCasa by remember { mutableStateOf(Usuario.peso?.toString() ?: "") }
//    var asumeGastosVeterinarios by remember { mutableStateOf(Usuario.peso?.toString() ?: "") }
//    var tiempoCalidad by remember { mutableStateOf(Usuario.peso?.toString() ?: "") }
//    var pisoOCasa by remember { mutableStateOf(Usuario.peso?.toString() ?: "") }
//

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                Text(
                    text = "Cuestionario"
                )
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

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