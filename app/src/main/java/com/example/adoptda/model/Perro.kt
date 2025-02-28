package com.example.adoptda.model

data class Perro(
    override val id: Int,
    override val nombre: String,
    override val imagen: Int,
    override val edad: Int,
    override val sexo: String,
    override  val descripcion: String,
    override  val provincia: String
): Animal
