package com.example.adoptda.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.adoptda.view.ui.theme.AdoptDATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdoptDATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // borrar este comentario
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "inicio") {
                        composable("inicio") { PantallaInicio(navController) }
                        composable("menu") { PantallaMenu(navController) }
                        composable("perros") { PantallaPerros(navController) }
                        composable("gatos") { PantallaGatos(navController) }
                        composable(
                            "adoptaGato/{gatoId}",
                            arguments = listOf(navArgument("gatoId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val gatoId = backStackEntry.arguments?.getInt("gatoId") ?: 0
                            PantallaAdopcionGato(navController, gatoId)
                        }
                        composable(
                            "adoptaPerro/{perroId}",
                            arguments = listOf(navArgument("perroId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val perroId = backStackEntry.arguments?.getInt("perroId") ?: 0
                            PantallaAdopcionPerro(navController, perroId)
                        }
                        composable("cuestionario") { PantallaCuestionario(navController) }
                    }
                }
            }
        }
    }
}
