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
import com.example.adoptda.model.BaseDatos
import com.example.adoptda.model.Usuario
import com.example.adoptda.view.ui.theme.AdoptDATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar la base de datos y crear un usuario vacío si no existe
        val baseDatos = BaseDatos(this)
        val usuarios = baseDatos.obtenerTodosUsuarios()
        if (usuarios.isEmpty()) {
            val usuarioVacio = Usuario(
                idUsuario = 0,
                nombre = "",
                apellido = "",
                dni = "",
                correo = "",
                masAnimales = false,
                experienciaPrevia = false,
                tiempoEnCasa = 0,
                gastosVeterinario = false,
                tiempoCalidad = false,
                pisoOCasa = false,
                animalesSolicitados = listOf()
            )
            baseDatos.insertarUsuario(usuarioVacio)
        }

        enableEdgeToEdge()
        setContent {
            AdoptDATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "inicio") {
                        composable("inicio") { PantallaInicio(navController) }
                        composable("menu") { PantallaMenu(navController) }
                        composable("perros") { PantallaPerros(navController) }
                        composable("gatos") { PantallaGatos(navController) }
                        composable(
                            "adoptaGato/{gatoId}",
                            arguments = listOf(navArgument("gatoId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val gatoId = backStackEntry.arguments?.getString("gatoId") ?: ""
                            PantallaAdopcionGato(navController, gatoId)
                        }
                        composable(
                            "adoptaPerro/{perroId}",
                            arguments = listOf(navArgument("perroId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val perroId = backStackEntry.arguments?.getString("perroId") ?: ""
                            PantallaAdopcionPerro(navController, perroId)
                        }
                        composable("cuestionario") {
                            PantallaCuestionario(navController)
                        }
                        composable("perfilesUsuario") { PerfilesUsuario(navController) }
                    }
                }
            }
        }
    }
}

