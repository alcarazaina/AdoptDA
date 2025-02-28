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
        db?.execSQL(CREATE_TABLE_SQL)

        // Insert a default user
        insertarUsuarioDefault(db)
    }

    private fun insertarUsuarioDefault(db: SQLiteDatabase?) {
        val values = ContentValues().apply {
            put(UsuarioTabla.COLUMN_NOMBRE, "")
            put(UsuarioTabla.COLUMN_APELLIDO, "")
            put(UsuarioTabla.COLUMN_DNI, "")
            put(UsuarioTabla.COLUMN_CORREO, "")
            put(UsuarioTabla.COLUMN_MAS_ANIMALES, 0)
            put(UsuarioTabla.COLUMN_EXPERIENCIA_PREVIA, 0)
            put(UsuarioTabla.COLUMN_TIEMPO_EN_CASA, 0)
            put(UsuarioTabla.COLUMN_GASTOS_VETERINARIO, 0)
            put(UsuarioTabla.COLUMN_TIEMPO_CALIDAD, 0)
            put(UsuarioTabla.COLUMN_PISO_O_CASA, 0)
            put(UsuarioTabla.COLUMN_ANIMALES_SOLICITADOS, "")
        }
        db?.insert(UsuarioTabla.TABLE_NAME, null, values)
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

                // Aquí está la corrección: verificamos si la cadena está vacía o es nula
                val animalesSolicitados = if (animalesSolicitadosString.isNullOrEmpty()) {
                    listOf()
                } else {
                    animalesSolicitadosString.split(",").filter { it.isNotEmpty() }.map { it.toInt() }
                }

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

            // Aplicamos la misma corrección aquí también
            val animalesSolicitados = if (animalesSolicitadosString.isNullOrEmpty()) {
                listOf()
            } else {
                animalesSolicitadosString.split(",").filter { it.isNotEmpty() }.map { it.toInt() }
            }

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

    fun obtenerUsuario(): Usuario {
        val db = this.readableDatabase
        val cursor = db.query(UsuarioTabla.TABLE_NAME, null, null, null, null, null, null)

        cursor.use {
            if (it.moveToFirst()) {
                return Usuario(
                    idUsuario = it.getInt(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_ID)),
                    nombre = it.getString(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_NOMBRE)),
                    apellido = it.getString(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_APELLIDO)),
                    dni = it.getString(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_DNI)),
                    correo = it.getString(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_CORREO)),
                    masAnimales = it.getInt(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_MAS_ANIMALES)) == 1,
                    experienciaPrevia = it.getInt(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_EXPERIENCIA_PREVIA)) == 1,
                    tiempoEnCasa = it.getInt(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_TIEMPO_EN_CASA)),
                    gastosVeterinario = it.getInt(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_GASTOS_VETERINARIO)) == 1,
                    tiempoCalidad = it.getInt(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_TIEMPO_CALIDAD)) == 1,
                    pisoOCasa = it.getInt(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_PISO_O_CASA)) == 1,
                    animalesSolicitados = it.getString(it.getColumnIndexOrThrow(UsuarioTabla.COLUMN_ANIMALES_SOLICITADOS))
                        .split(",").filter { it.isNotEmpty() }.map { it.toInt() }
                )
            }
        }

        // Si no existe un usuario, devolver null en lugar de crear uno nuevo
        return Usuario(
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
    }
    fun crearUsuarioConAnimal(animalId: Int): Usuario {
        val usuario = Usuario(
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
            animalesSolicitados = listOf(animalId)
        )
        val id = insertarUsuario(usuario)
        return usuario.copy(idUsuario = id.toInt())
    }

    // Replace the existing actualizarUsuario method with this one
    fun actualizarUsuarioCompleto(usuario: Usuario): Boolean {
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

        val rowsAffected = db.update(UsuarioTabla.TABLE_NAME, values, null, null)
        db.close()
        return rowsAffected > 0
    }

    fun resetearUsuario() {
        val db = this.writableDatabase
        db.delete(UsuarioTabla.TABLE_NAME, null, null)
        insertarUsuarioDefault(db)
        db.close()
    }

    // Update the agregarSolicitudAdopcion method to use actualizarUsuarioCompleto
    fun agregarSolicitudAdopcion(animalId: Int): Boolean {
        val usuario = obtenerUsuario()
        val nuevaListaSolicitudes = usuario.animalesSolicitados + animalId
        val usuarioActualizado = usuario.copy(animalesSolicitados = nuevaListaSolicitudes)
        return actualizarUsuarioCompleto(usuarioActualizado)
    }

    companion object {
        private const val DATABASE_NAME = "usuario.db"
        private const val DATABASE_VERSION = 1

        private const val CREATE_TABLE_SQL = """
            CREATE TABLE ${UsuarioTabla.TABLE_NAME} (
                ${UsuarioTabla.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                ${UsuarioTabla.COLUMN_NOMBRE} TEXT NOT NULL,
                ${UsuarioTabla.COLUMN_APELLIDO} TEXT NOT NULL,
                ${UsuarioTabla.COLUMN_DNI} TEXT NOT NULL,
                ${UsuarioTabla.COLUMN_CORREO} TEXT NOT NULL,
                ${UsuarioTabla.COLUMN_MAS_ANIMALES} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_EXPERIENCIA_PREVIA} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_TIEMPO_EN_CASA} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_GASTOS_VETERINARIO} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_TIEMPO_CALIDAD} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_PISO_O_CASA} INTEGER NOT NULL,
                ${UsuarioTabla.COLUMN_ANIMALES_SOLICITADOS} TEXT
            )
        """
    }
}

