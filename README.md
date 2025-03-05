# 🐶🐱 Plataforma de Adopción de Perros y Gatos

Este es un proyecto para gestionar adopciones de perros y gatos, permitiendo a los usuarios ver perfiles de mascotas disponibles, completar cuestionarios de adopción y navegar fácilmente por las opciones disponibles.

## 🚀 Características
- Listado de perros y gatos disponibles para adopción.
- Pantallas detalladas para cada mascota con información relevante.
- Cuestionario para evaluar la compatibilidad del adoptante con la mascota.
- Gestión de usuarios y sus perfiles.
- Base de datos para almacenar información relevante.

## 🎮 Cómo usar la aplicación
- Abre la aplicación y elige si quieres adoptar un perro o un gato pulsando el botón correspondiente.
- Aparecerá una lista de mascotas disponibles en tarjetas con su información.
- Selecciona una mascota de la lista y pulsa el botón + para comenzar el proceso de adopción.
- Se te mostrará un cuestionario que debes completar para evaluar tu compatibilidad con la mascota.
- Una vez rellenado el cuestionario, pulsa nuevamente + para añadir la mascota a tu perfil de adopciones solicitadas.
- Puedes volver a la lista de mascotas y añadir más presionando el + en el perfil de cada animal que desees adoptar.

## 🛠 Tecnologías utilizadas
- Lenguaje de programación: Kotlin
- Framework UI: Jetpack Compose
- Arquitectura: MVVM
- Base de datos: MySQLite (SQLiteOpenHelper)

## 👩🏼‍💻 Integrantes
- Ainara (@alcarazaina)
- Juan Daniel (@juanda1612)

## 📜 Historial de Commits y Contribuciones
A continuación, se detallan los cambios realizados en el proyecto junto con las contribuciones de cada persona:

📅 1 de Marzo de 2025
```md
- **@alcarazaina**: Arreglado que cuando rellenes el cuestionario no te lleve al perfil, sino a la pantalla anterior para que solicites la adopción de la mascota elegida.
```

📅 28 de Febrero de 2025
```md
- **@alcarazaina**: Hecho el `ProgressBar` de las pantallas `PantallaAdopcionGato.kt` y `PantallaAdopcionPerro.kt`, añadido los strings necesarios para las nuevas barras de progreso y la antigua.
- **@juanda1612**: Funcionalidad completa, ya detecta si es gato o perro, cambiando simplemente las IDs.
- **@juanda1612**: Cambios en el código para añadir gatos al usuario.
- **@alcarazaina**: Solucionados problemas con la base de datos en la creación de usuarios.
```

📅 27 de Febrero de 2025
```md
- **@juanda1612**: Comienzo de la segunda entrega, implementada base de datos y creación de usuarios.
```

### 📅 24 de Febrero de 2025
```md
- **@juanda1612**: Se completó todo el desarrollo pendiente de la primera entrega.
```

### 📅 23 de Febrero de 2025
```md
- **@alcarazaina**: Se incluyó ajustes en los recursos de strings, cambió de colores de botones y funcionalidad del cuestionario en la pantalla de perros.
```

### 📅 22 de Febrero de 2025
```md
- **@juanda1612**: Se creó el archivo `BaseDatos.kt` (sin código por el momento) y `PerfilesUsuario.kt`.
```

### 📅 21 de Febrero de 2025
```md
- **@juanda1612**: Se creó la funcionalidad del cuestionario junto con `PreguntasCuestionario.kt`.
```

### 📅 20 de Febrero de 2025
```md
- **@juanda1612**: Se desarrolló `PantallaCuestionario.kt`, la clase `Usuario` y se añadió un diálogo en la adopción de perros y gatos.
```

### 📅 18 de Febrero de 2025
```md
- **@alcarazaina**: Se avanzó en el desarrollo de la pantalla de perros.
```

### 📅 14 de Febrero de 2025
```md
- **@alcarazaina**: Se trabajó en la pantalla de perros.
```

### 📅 10 de Febrero de 2025
```md
- **@juanda1612**: Se creó `PantallaAdopcionPerro.kt`, replicando la funcionalidad previamente hecha para gatos.
```

### 📅 7 de Febrero de 2025
```md
- **@alcarazaina**: Se agregó la descripción de gatos y la opción de añadir la provincia del adoptante.
```

### 📅 6 de Febrero de 2025
```md
- **@juanda1612**: Creación del objeto `PerroRepository` para manejar la lista de perros.
- **@juanda1612**: Creación del objeto `GatoRepository` y `PantallaAdopcionGato.kt` para mostrar la información de los gatos seleccionados.
```

### 📅 4 de Febrero de 2025
```md
- **@alcarazaina**: Mejora en los títulos de las pantallas de perros y gatos.
```

### 📅 31 de Enero de 2025
```md
- **@alcarazaina**: Implementación de pantallas para perros y gatos, con tarjetas e imágenes.
```

### 📅 29 de Enero de 2025
```md
- **@alcarazaina**: Se añadieron imágenes al proyecto.
```

### 📅 Inicial
```md
- **@juanda1612**: Commit inicial con la estructura base del proyecto.
```

## 💖 Agradecimientos

Queremos agradecer a todos los que nos han apoyado con este proyecto. Esperamos que esta aplicación ayude a conectar muchas mascotas con hogares llenos de amor. 🏡🐾 ¡Gracias por tomarte el tiempo de conocer nuestra iniciativa y formar parte de ella! 🎉
