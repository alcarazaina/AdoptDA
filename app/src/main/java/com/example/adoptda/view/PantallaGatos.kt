package com.example.adoptda.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adoptda.R
import com.example.adoptda.model.Gato
import com.example.adoptda.view.ui.theme.Pink

@Composable
fun PantallaGatos(navController: NavController) {
    val gatos = listOf(
        Gato(1, "Simba", R.drawable.simba),
        Gato(2, "Alaska", R.drawable.alaska),
        Gato(3, "Greta", R.drawable.greta),
        Gato(4, "Lana", R.drawable.lana),
        Gato(5, "Lola y Lolitos", R.drawable.lolaylolitos),
        Gato(6, "Lucifer", R.drawable.lucifer),
        Gato(7, "Miel", R.drawable.miel),
        Gato(8, "Pompom", R.drawable.pompom)

    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.gatotitulo),
            modifier = Modifier.padding(32.dp),
            style = TextStyle(
                fontSize = 60.sp,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight(weight = 500)
            )
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(gatos) { gatos ->
                    GatoCard(gatos)
                }
            }
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(Pink)
            ) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Regresar", tint = Color.White)
            }
        }
    }
}

@Composable
fun GatoCard(gato: Gato) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {/*aqui ira la alerta de adopcion*/ },
        shape = RoundedCornerShape(30.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
        )
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(id = gato.imagen),
                contentDescription = gato.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = gato.nombre
            )
        }
    }
}

