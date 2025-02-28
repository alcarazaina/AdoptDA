package com.example.adoptda.model

data class Usuario(
    val idUsuario: Int,
    val nombre: String,
    val apellido: String,
    val dni: String,
    val correo: String,
    val masAnimales: Boolean,
    val experienciaPrevia: Boolean,
    val tiempoEnCasa: Int,
    val gastosVeterinario: Boolean,
    val tiempoCalidad: Boolean,
    val pisoOCasa: Boolean,
    val animalesSolicitados: List<Any>
)
