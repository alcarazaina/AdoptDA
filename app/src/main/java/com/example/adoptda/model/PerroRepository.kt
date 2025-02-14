package com.example.adoptda.model

import com.example.adoptda.R
import com.example.adoptda.model.GatoRepository.gatos

object PerroRepository {
    val perros = listOf(
        Perro(1, "Duquesa", R.drawable.duquesa, 5, "Hembra", "Duquesa es una perrita de 5 años que fue regalada a un niño como obsequio de Reyes. Sin embargo, cuando sus padres vieron que cuidar de ella requería demasiada responsabilidad, decidieron abandonarla en la basura. A pesar de su triste pasado, Duquesa sigue siendo una perrita noble, cariñosa y leal, que solo quiere encontrar un hogar donde ser valorada y amada como merece. ¿Serás tú quien le brinde esa segunda oportunidad?", "Murcia"),
        Perro(2, "Golfo", R.drawable.golfo, 1, "Macho", "Golfo es un perrito de 1 año que fue rescatado de un local donde organizaban peleas de perros. Cuando lo encontraron, estaba en un estado crítico: desnutrido y tan débil que apenas podía caminar. Gracias a los cuidados recibidos, ha logrado recuperarse y ahora está listo para encontrar un hogar lleno de amor y seguridad. Golfo es un ejemplo de resiliencia y solo necesita una familia que le brinde el cariño que nunca ha tenido. ¿Te animas a cambiar su destino?", "Barcelona"),
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