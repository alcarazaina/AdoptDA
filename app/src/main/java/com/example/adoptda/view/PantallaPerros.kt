package com.example.adoptda.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
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
import com.example.adoptda.model.Perro
import com.example.adoptda.view.ui.theme.Pink

@Composable
fun PantallaPerros(navController: NavController) {
    val perros = listOf(
        Perro(1, "Duquesa", R.drawable.duquesa),
        Perro(2, "Golfo", R.drawable.golfo),
        Perro(3, "Gordon", R.drawable.gordon),
        Perro(4, "Kiko", R.drawable.kiko),
        Perro(5, "Laika", R.drawable.laika),
        Perro(6, "Nano", R.drawable.nano),
        Perro(7, "Pipo", R.drawable.pipo),
        Perro(8, "Toby", R.drawable.tobi),
    )
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 50.dp, bottom = 16.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(color = Pink),
            contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(R.string.perrotitulo),
                modifier = Modifier.padding(32.dp),
                style = TextStyle(
                    fontSize = 60.sp,
                    color = Color.White,
                    fontWeight = FontWeight(weight = 500)
                )
            )
        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(perros) { perros ->
                    PerroCard(perros)
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
fun PerroCard(perro: Perro) {
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
                painter = painterResource(id = perro.imagen),
                contentDescription = perro.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = perro.nombre
            )
        }
    }
}