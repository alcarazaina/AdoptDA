package com.example.adoptda.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.adoptda.R
import com.example.adoptda.view.ui.theme.Pink
import com.example.adoptda.viewmodel.InicioViewModel

@Composable
fun PantallaInicio(navController: NavController, viewModel: InicioViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.shouldNavigateToMenu) {
        navController.navigate("menu")
        viewModel.onNavigatedToMenu()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo de la aplicación",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { viewModel.onComenzarClick() },
            modifier = Modifier,
            colors = ButtonDefaults.buttonColors(Pink)
        ) {
            Text(stringResource(R.string.comenzar),
                style = TextStyle(Color.White)
            )
        }
    }
}
