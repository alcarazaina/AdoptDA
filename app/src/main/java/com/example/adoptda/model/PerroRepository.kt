package com.example.adoptda.model

import com.example.adoptda.R
import com.example.adoptda.model.GatoRepository.gatos

object PerroRepository {
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

    fun getPerroById(id: Int): Perro? {
        return perros.find { it.id == id }
    }
}