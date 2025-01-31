package com.example.adoptda.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adoptda.R
import com.example.adoptda.view.ui.theme.Pink

@Composable
fun PantallaMenu(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navController.navigate("perros") },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(Pink)
        ) {
            Text(stringResource(R.string.perros),
                style = TextStyle(Color.White)
            )
        }
        Button(
            onClick = { navController.navigate("gatos") },
            modifier = Modifier.padding(8.dp),
            colors = ButtonDefaults.buttonColors(Pink)
        ) {
            Text(stringResource(R.string.gatos),
                style = TextStyle(Color.White)
            )
        }
    }
}