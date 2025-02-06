package com.example.adoptda.model

import com.example.adoptda.R

object GatoRepository {
    val gatos = listOf(
        Gato(1, "Simba", R.drawable.simba,1,"Chico","Gatete"),
        Gato(2, "Alaska", R.drawable.alaska,1,"Chico","Gatete"),
        Gato(3, "Greta", R.drawable.greta,1,"Chico","Gatete"),
        Gato(4, "Lana", R.drawable.lana,1,"Chico","Gatete"),
        Gato(5, "Lola y Lolitos", R.drawable.lolaylolitos,1,"Chico","Gatete"),
        Gato(6, "Lucifer", R.drawable.lucifer,1,"Chico","Gatete"),
        Gato(7, "Miel", R.drawable.miel,1,"Chico","Gatete"),
        Gato(8, "Pompom", R.drawable.pompom,1,"Chico","Gatete")
    )

    fun getGatoById(id: Int): Gato? {
        return gatos.find { it.id == id }
    }
}