package com.example.adoptda.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    // Definimos la estructura de la tabla usuarios
    object UsuarioTabla {
        const val TABLE_NAME = "usuarios"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_APELLIDO = "apellido"
        const val COLUMN_DNI = "dni"
        const val COLUMN_CORREO = "correo"
        const val COLUMN_MAS_ANIMALES = "mas_animales"
        const val COLUMN_EXPERIENCIA_PREVIA = "experiencia_previa"
        const val COLUMN_TIEMPO_EN_CASA = "tiempo_en_casa"
        const val COLUMN_GASTOS_VETERINARIO = "gastos_veterinario"
        const val COLUMN_TIEMPO_CALIDAD = "tiempo_calidad"
        const val COLUMN_PISO_O_CASA = "piso_o_casa"
        const val COLUMN_ANIMALES_SOLICITADOS = "animales_solicitados"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Creamos la tabla usuarios
        val createTableSQL = """
            CREATE TABLE ${UsuarioTabla.TABLE_NAME} (
                ${UsuarioTabla.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${UsuarioTabla.COLUMN_NOMBRE} TEXT NOT NULL,
                ${UsuarioTabla.COLUMN_APELLIDO} TEXT NOT NULL,
                ${UsuarioTabla.COLUMN_DNI} TEXT NOT NULL UNIQUE,
                ${UsuarioTabla.COLUMN_CORREO} TEXT NOT NULL UNIQUE,
                ${UsuarioTabla.COLUMN_MAS_ANIMALES} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_EXPERIENCIA_PREVIA} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_TIEMPO_EN_CASA} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_GASTOS_VETERINARIO} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_TIEMPO_CALIDAD} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_PISO_O_CASA} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_ANIMALES_SOLICITADOS} TEXT
            )
        """.trimIndent()

        db?.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // En caso de actualizar la versión de la base de datos, eliminamos la tabla y la volvemos a crear
        db?.execSQL("DROP TABLE IF EXISTS ${UsuarioTabla.TABLE_NAME}")
        onCreate(db)
    }

    // Método para insertar un nuevo usuario
    fun insertarUsuario(usuario: Usuario): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            // No incluimos ID porque es autoincremental
            put(UsuarioTabla.COLUMN_NOMBRE, usuario.nombre)
            put(UsuarioTabla.COLUMN_APELLIDO, usuario.apellido)
            put(UsuarioTabla.COLUMN_DNI, usuario.dni)
            put(UsuarioTabla.COLUMN_CORREO, usuario.correo)
            put(UsuarioTabla.COLUMN_MAS_ANIMALES, if (usuario.masAnimales) 1 else 0)
            put(UsuarioTabla.COLUMN_EXPERIENCIA_PREVIA, if (usuario.experienciaPrevia) 1 else 0)
            put(UsuarioTabla.COLUMN_TIEMPO_EN_CASA, usuario.tiempoEnCasa)
            put(UsuarioTabla.COLUMN_GASTOS_VETERINARIO, if (usuario.gastosVeterinario) 1 else 0)
            put(UsuarioTabla.COLUMN_TIEMPO_CALIDAD, if (usuario.tiempoCalidad) 1 else 0)
            put(UsuarioTabla.COLUMN_PISO_O_CASA, if (usuario.pisoOCasa) 1 else 0)
            put(UsuarioTabla.COLUMN_ANIMALES_SOLICITADOS, usuario.animalesSolicitados.joinToString(","))
        }

        val id = db.insert(UsuarioTabla.TABLE_NAME, null, values)
        db.close()
        return id
    }

    // Método para obtener todos los usuarios
    fun obtenerTodosUsuarios(): List<Usuario> {
        val listaUsuarios = mutableListOf<Usuario>()
        val db = this.readableDatabase
        val query = "SELECT * FROM ${UsuarioTabla.TABLE_NAME}"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_ID))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_NOMBRE))
                val apellido = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_APELLIDO))
                val dni = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_DNI))
                val correo = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_CORREO))
                val masAnimales = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_MAS_ANIMALES)) == 1
                val experienciaPrevia = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_EXPERIENCIA_PREVIA)) == 1
                val tiempoEnCasa = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_TIEMPO_EN_CASA))
                val gastosVeterinario = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_GASTOS_VETERINARIO)) == 1
                val tiempoCalidad = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_TIEMPO_CALIDAD)) == 1
                val pisoOCasa = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_PISO_O_CASA)) == 1
                val animalesSolicitadosString = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_ANIMALES_SOLICITADOS))
                val animalesSolicitados = animalesSolicitadosString?.split(",")?.map { it.toInt() } ?: listOf()

                val usuario = Usuario(
                    idUsuario = id,
                    nombre = nombre,
                    apellido = apellido,
                    dni = dni,
                    correo = correo,
                    masAnimales = masAnimales,
                    experienciaPrevia = experienciaPrevia,
                    tiempoEnCasa = tiempoEnCasa,
                    gastosVeterinario = gastosVeterinario,
                    tiempoCalidad = tiempoCalidad,
                    pisoOCasa = pisoOCasa,
                    animalesSolicitados = animalesSolicitados
                )

                listaUsuarios.add(usuario)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()
        return listaUsuarios
    }

    // Método para obtener un usuario por su ID
    fun obtenerUsuarioPorId(id: Int): Usuario? {
        val db = this.readableDatabase
        val query = "SELECT * FROM ${UsuarioTabla.TABLE_NAME} WHERE ${UsuarioTabla.COLUMN_ID} = ?"
        val cursor: Cursor = db.rawQuery(query, arrayOf(id.toString()))

        var usuario: Usuario? = null

        if (cursor.moveToFirst()) {
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_NOMBRE))
            val apellido = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_APELLIDO))
            val dni = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_DNI))
            val correo = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_CORREO))
            val masAnimales = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_MAS_ANIMALES)) == 1
            val experienciaPrevia = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_EXPERIENCIA_PREVIA)) == 1
            val tiempoEnCasa = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_TIEMPO_EN_CASA))
            val gastosVeterinario = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_GASTOS_VETERINARIO)) == 1
            val tiempoCalidad = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_TIEMPO_CALIDAD)) == 1
            val pisoOCasa = cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_PISO_O_CASA)) == 1
            val animalesSolicitadosString = cursor.getString(cursor.getColumnIndexOrThrow(UsuarioTabla.COLUMN_ANIMALES_SOLICITADOS))
            val animalesSolicitados = animalesSolicitadosString?.split(",")?.map { it.toInt() } ?: listOf()

            usuario = Usuario(
                idUsuario = id,
                nombre = nombre,
                apellido = apellido,
                dni = dni,
                correo = correo,
                masAnimales = masAnimales,
                experienciaPrevia = experienciaPrevia,
                tiempoEnCasa = tiempoEnCasa,
                gastosVeterinario = gastosVeterinario,
                tiempoCalidad = tiempoCalidad,
                pisoOCasa = pisoOCasa,
                animalesSolicitados = animalesSolicitados
            )
        }

        cursor.close()
        db.close()
        return usuario
    }

    // Método para actualizar un usuario
    fun actualizarUsuario(usuario: Usuario): Int {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(UsuarioTabla.COLUMN_NOMBRE, usuario.nombre)
            put(UsuarioTabla.COLUMN_APELLIDO, usuario.apellido)
            put(UsuarioTabla.COLUMN_DNI, usuario.dni)
            put(UsuarioTabla.COLUMN_CORREO, usuario.correo)
            put(UsuarioTabla.COLUMN_MAS_ANIMALES, if (usuario.masAnimales) 1 else 0)
            put(UsuarioTabla.COLUMN_EXPERIENCIA_PREVIA, if (usuario.experienciaPrevia) 1 else 0)
            put(UsuarioTabla.COLUMN_TIEMPO_EN_CASA, usuario.tiempoEnCasa)
            put(UsuarioTabla.COLUMN_GASTOS_VETERINARIO, if (usuario.gastosVeterinario) 1 else 0)
            put(UsuarioTabla.COLUMN_TIEMPO_CALIDAD, if (usuario.tiempoCalidad) 1 else 0)
            put(UsuarioTabla.COLUMN_PISO_O_CASA, if (usuario.pisoOCasa) 1 else 0)
            put(UsuarioTabla.COLUMN_ANIMALES_SOLICITADOS, usuario.animalesSolicitados.joinToString(","))
        }

        val filas = db.update(
            UsuarioTabla.TABLE_NAME,
            values,
            "${UsuarioTabla.COLUMN_ID} = ?",
            arrayOf(usuario.idUsuario.toString())
        )

        db.close()
        return filas
    }

    // Método para eliminar un usuario
    fun eliminarUsuario(id: Int): Int {
        val db = this.writableDatabase
        val filas = db.delete(
            UsuarioTabla.TABLE_NAME,
            "${UsuarioTabla.COLUMN_ID} = ?",
            arrayOf(id.toString())
        )

        db.close()
        return filas
    }

    fun agregarSolicitudAdopcion(usuarioId: Int, animalId: Int): Boolean {
        val usuario = obtenerUsuarioPorId(usuarioId) ?: return false
        val nuevaListaSolicitudes = usuario.animalesSolicitados + animalId
        val usuarioActualizado = usuario.copy(animalesSolicitados = nuevaListaSolicitudes)
        return actualizarUsuario(usuarioActualizado) > 0
    }

    companion object {
        private const val DATABASE_NAME = "usuarios.db"
        private const val DATABASE_VERSION = 1
    }
}

