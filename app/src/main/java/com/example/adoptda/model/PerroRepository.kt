package com.example.adoptda.model

import com.example.adoptda.R
import com.example.adoptda.model.GatoRepository.gatos

object PerroRepository {
    val perros = listOf(
        Perro("p1", "Duquesa", R.drawable.duquesa, 5, "Hembra", "Duquesa es una perrita de 5 años que fue regalada a un niño como obsequio de Reyes. Sin embargo, cuando sus padres vieron que cuidar de ella requería demasiada responsabilidad, decidieron abandonarla en la basura. A pesar de su triste pasado, Duquesa sigue siendo una perrita noble, cariñosa y leal, que solo quiere encontrar un hogar donde ser valorada y amada como merece. ¿Serás tú quien le brinde esa segunda oportunidad?", "Murcia"),
        Perro("p2", "Golfo", R.drawable.golfo, 1, "Macho", "Golfo es un perrito de 1 año que fue rescatado de un local donde organizaban peleas de perros. Cuando lo encontraron, estaba en un estado crítico: desnutrido y tan débil que apenas podía caminar. Gracias a los cuidados recibidos, ha logrado recuperarse y ahora está listo para encontrar un hogar lleno de amor y seguridad. Golfo es un ejemplo de resiliencia y solo necesita una familia que le brinde el cariño que nunca ha tenido. ¿Te animas a cambiar su destino?", "Barcelona"),
        Perro("p3", "Gordon", R.drawable.gordon, 10, "Macho", "Gordon es un perro guía jubilado de 10 años que ha dedicado su vida a ayudar a su humano con discapacidad visual. Ahora, debido a un problema en una de sus patitas, ha dejado de trabajar y busca un hogar en Madrid donde pueda pasar su retiro rodeado de amor y cuidados. Gordon es un perro noble, tranquilo y extremadamente leal, que solo necesita una familia que le brinde el descanso y el cariño que tanto merece. ¿Quieres ser tú quien le ofrezca un hogar para su merecida jubilación?", "Madrid"),
        Perro("p4", "Kiko", R.drawable.kiko, 11, "Macho", "Kiko es un perrito de 11 años que, por circunstancias ajenas a él, ha perdido a su familia. Su dueña, con problemas personales, ya no puede hacerse cargo de él y ahora busca un nuevo hogar donde pueda pasar sus años dorados con amor y tranquilidad. Kiko es un perro noble, agradecido y de corazón tierno. ¿Podrías darle la oportunidad de vivir sus últimos años rodeado de cariño?", "Cáceres"),
        Perro("p5", "Laika", R.drawable.laika, 4, "Hembra", "Laika es una perrita de 5 años que fue encontrada en una situación desgarradora: atada en medio de la carretera, desnutrida y en muy mal estado. Gracias a su casa de acogida, ha recuperado su peso y su hermoso pelaje brillante. Ahora, está lista para encontrar un hogar definitivo donde recibir amor y nunca más sufrir abandono. ¿Quieres ser tú quien le brinde la vida que siempre mereció?", "San Sebastián"),
        Perro("p6", "Nano", R.drawable.nano, 0, "Macho", "Nano es un cachorro de solo 4 meses que fue encontrado en una bolsa atada dentro de la basura, junto con su hermana. Afortunadamente, alguien los oyó llorar y los rescató. Su hermana por desgracia no sobrevivió. Nano tuvó suerte pero aún espera su oportunidad. Es un perrito tierno, juguetón y lleno de vida, que solo necesita una familia que lo ame para siempre. ¿Quieres ser tú quien le dé el hogar que tanto merece?", "Albacete"),
        Perro("p7", "Pipo", R.drawable.pipo,13, "Macho", "Pipo es un perrito de 13 años que ha vivido una gran tragedia. Sus dueños fallecieron en un accidente de coche, y solo sobrevivió el niño de la familia, de 10 años, quien lamentablemente no puede hacerse cargo de él. Ahora, Pipo busca un hogar donde pasar sus últimos años rodeado de amor y cuidados. Es un perro leal y tranquilo, que solo necesita una familia que le brinde el cariño que perdió. ¿Podrías ser tú esa familia?", "Alicante"),
        Perro("p8", "Toby", R.drawable.tobi, 3, "Macho", "Toby es un perrito de 3 años que sufrió una historia desgarradora. Su dueño, un toxicómano, lo intercambió por una dosis, y su \"nuevo dueño\" lo abandonó en la calle. A pesar de su pasado, Toby sigue confiando en los humanos y busca un hogar donde pueda recibir el amor que nunca tuvo. ¿Serás tú quien le brinde la oportunidad de un nuevo comienzo?", "Zaragoza"),
    )

    fun getPerroById(id: String): Perro? {
        return perros.find { it.id == id }
    }

    // Método adicional para compatibilidad con código existente que use IDs numéricos
    fun getPerroById(id: Int): Perro? {
        return perros.find { it.id == "p$id" }
    }
}