package com.example.adoptda.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoptda.R
import com.example.adoptda.view.ui.theme.Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMenu(navController: NavController) {
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

        // Scaffold and content
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.queadoptas),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = Pink,
                        titleContentColor = Color.White,
                    )
                )
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = Pink,
                    contentColor = Color.White
                ) {
                    Text(
                        text = stringResource(R.string.textobottom),
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = Color.White),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            },
            containerColor = Color.Transparent // Make scaffold background transparent
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate("perros") },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(Pink)
                ) {
                    Text(
                        stringResource(R.string.perros),
                        style = TextStyle(Color.White)
                    )
                }
                Button(
                    onClick = { navController.navigate("gatos") },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(Pink)
                ) {
                    Text(
                        stringResource(R.string.gatos),
                        style = TextStyle(Color.White)
                    )
                }
                Button(
                    onClick = { navController.navigate("perfilesUsuario") },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(0.8f),
                    colors = ButtonDefaults.buttonColors(Pink)
                ) {
                    Text(
                        stringResource(R.string.perfilesUs),
                        style = TextStyle(Color.White)
                    )
                }
            }
        }
    }
}