package com.example.adoptda.model

import com.example.adoptda.R

object GatoRepository {
    val gatos = listOf(
        Gato("g1", "Simba", R.drawable.simba,1,"Macho","Gato joven, tranquilo, cariñoso y jugueton. \nSimba, un gato con ojos llenos de esperanza, pasó meses en las calles hasta que fue rescatado y llevado a un refugio. A pesar de su pasado difícil, es amoroso y juguetón, siempre en busca de caricias. Ahora, espera un hogar donde pueda ser amado y brindar alegría. ¿Serás tú quien le dé su final feliz?", "Madrid"),
        Gato("g2", "Alaska", R.drawable.alaska,3,"Hembra","Alaska es una hermosa gata blanca de 3 años que enamora con su ternura y elegancia. Con un carácter tranquilo y cariñoso, disfruta de las caricias y la compañía. Fue rescatada en busca de una segunda oportunidad, despúes de haber sido abandonada por su anterior familia. Ahora espera un hogar donde pueda recibir el amor que merece. Si buscas una compañera fiel y amorosa, Alaska es la indicada. \nCompatible con otras mascotas y niños", "Valencia"),
        Gato("g3", "Greta", R.drawable.greta,4,"Hembra","Greta es una gata de 4 años que ha demostrado una increíble fortaleza. Fue rescatada de una tienda donde la usaban para criar gatos de raza sin recibir el cuidado y amor que merecía, metida 24/7 en una jaula donde tenia a sus cachorros en cada celo. A pesar de su pasado, Greta es una gatita dulce y agradecida, que busca un hogar donde pueda sentirse segura y querida. Ahora tiene la oportunidad de conocer el amor verdadero, ¿quieres ser tú quien le brinde esa oportunidad?", "Barcelona"),
        Gato("g4", "Lana", R.drawable.lana,2,"Hembra","Lana es una hermosa gata de 2 años, de pelaje marrón y blanco, que ha sufrido una gran pérdida. Su dueña falleció y, desde entonces, se ha quedado sin familia. A pesar de la tristeza, Lana sigue siendo una gatita cariñosa y noble que solo quiere un nuevo hogar donde sentirse amada. ¿Te gustaría brindarle una segunda oportunidad?. \nCompatible con otras mascotas", "Madrid"),
        Gato("g5", "Lola y Lolitos", R.drawable.lolaylolitos,4,"Hembra","Lola es una gata tricolor de 4 años que ha vivido momentos muy duros. Fue encontrada en la basura, buscando comida desesperadamente mientras protegía a sus pequeños recién nacidos, los Lolitos. Debido al maltrato que sufrió por parte de algunos jóvenes en la calle, es muy asustadiza con los humanos, pero con paciencia y amor, puede aprender a confiar de nuevo. Lola y sus bebés necesitan un hogar seguro donde crecer y recibir el cariño que nunca han tenido. ¿Serás tú quien les dé la oportunidad de una vida mejor? \nNo compatible con niños, ni otras mascotas", "Valencia"),
        Gato("g6", "Lucifer", R.drawable.lucifer,1,"Macho","Lucifer es un gato negro de 1 año que fue rescatado de una situación aterradora. Algunas personas supersticiosas querían hacer experimentos con él y sacrificarlo en rituales, simplemente por el color de su pelaje. Afortunadamente, fue salvado a tiempo y ahora busca un hogar donde pueda recibir el amor y la protección que merece. A pesar de su pasado, Lucifer es un gato noble y cariñoso, listo para demostrar que los gatos negros traen felicidad, no mala suerte. ¿Le darás la oportunidad de un futuro brillante?", "Madrid"),
        Gato("g7", "Miel", R.drawable.miel,3,"Macho","Miel es un gato de 3 años, de pelaje anaranjado, con la personalidad rebelde y juguetona típica de los gatos de su color. Siempre está explorando y buscando nuevas aventuras, pero también disfruta de los mimos y la compañía humana. Si buscas un compañero activo y divertido, Miel es el gato perfecto para ti.", "A Coruña"),
        Gato("g8", "Pompom", R.drawable.pompom,2,"Hembra","Pompom es un hermoso gato negro de pelo largo y 2 años de edad. Su carácter tranquilo le costó su hogar, ya que sus antiguos dueños adoptaron un perro inquieto que no lo dejaba en paz. Al defenderse, Pompom arañaba al perro, y sus dueños decidieron abandonarlo en casa de un vecino con solo una nota. Ahora, este dulce y sereno gatito necesita un hogar donde pueda vivir en calma y recibir el amor que realmente merece. ¿Serás tú quien le dé esa oportunidad?", "A Coruña")
    )

    fun getGatoById(id: String): Gato? {
        return gatos.find { it.id == id }
    }

    // Método adicional para compatibilidad con código existente que use IDs numéricos
    fun getGatoById(id: Int): Gato? {
        return gatos.find { it.id == "g$id" }
    }
}