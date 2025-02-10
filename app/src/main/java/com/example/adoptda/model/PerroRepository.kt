package com.example.adoptda.model

import com.example.adoptda.R
import com.example.adoptda.model.GatoRepository.gatos

object PerroRepository {
    val perros = listOf(
        Perro(1, "Duquesa", R.drawable.duquesa, 5, "Hembra", " ", "Murcia"),
        Perro(2, "Golfo", R.drawable.golfo, 1, "Macho", " ", "Barcelona"),
        Perro(3, "Gordon", R.drawable.gordon, 2, "Macho", " ", "Madrid"),
        Perro(4, "Kiko", R.drawable.kiko, 11, "Macho", " ", "Cáceres"),
        Perro(5, "Laika", R.drawable.laika, 4, "Hembra", " ", "San Sebastián"),
        Perro(6, "Nano", R.drawable.nano, 0, "Macho", " ", "Albacete"),
        Perro(7, "Pipo", R.drawable.pipo,13, "Macho", " ", "Alicante"),
        Perro(8, "Toby", R.drawable.tobi, 3, "Macho", " ", "Zaragoza"),
    )

    fun getPerroById(id: Int): Perro? {
        return perros.find { it.id == id }
    }
}