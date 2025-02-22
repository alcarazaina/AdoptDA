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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoptda.R
import com.example.adoptda.model.PreguntaBoolean
import com.example.adoptda.model.PreguntaTextField
import com.example.adoptda.model.PreguntaTiempo
import com.example.adoptda.view.ui.theme.Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilesUsuario(navController: NavController) {
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
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Usuarios",
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
        ){ innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            }
        }

    }
}