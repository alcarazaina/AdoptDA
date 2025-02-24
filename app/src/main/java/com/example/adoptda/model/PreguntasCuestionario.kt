package com.example.adoptda.model

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adoptda.R
import com.example.adoptda.view.ui.theme.Pink

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreguntaTextField(
    enunciado: String,
    nombre: String,
    onNombreChange: (String) -> Unit,
    onValidationChange: (Boolean) -> Unit
) {
    val pattern = when {
        enunciado.contains("DNI", ignoreCase = true) -> Regex("^\\d{8}[A-Za-z]$")
        enunciado.contains("correo", ignoreCase = true) -> Regex("^[^@]+@[^@]+\\.[^@]+$")
        else -> Regex("^[^0-9]+$")
    }

    val isValid = nombre.matches(pattern)
    val isDni = enunciado.contains("DNI", ignoreCase = true)
    val isCorreo = enunciado.contains("correo", ignoreCase = true)

    LaunchedEffect(nombre) {
        onValidationChange(isValid)
    }

    Column {
        OutlinedTextField(
            value = nombre,
            onValueChange = { newValue ->
                when {
                    isDni -> {
                        val dniNumbers = newValue.take(8).filter { it.isDigit() }
                        val dniLetter = newValue.drop(8).take(1).filter { it.isLetter() }
                        val newDni = dniNumbers + dniLetter
                        if (newDni.length <= 9) {
                            onNombreChange(newDni)
                        }
                    }
                    isCorreo -> {
                        onNombreChange(newValue)
                    }
                    else -> {
                        if (newValue.all { !it.isDigit() }) {
                            onNombreChange(newValue)
                        }
                    }
                }
            },
            label = {
                Text(
                    enunciado,
                    style = TextStyle(color = Color.Black)
                )
            },
            isError = nombre.isNotEmpty() && !isValid,
            textStyle = TextStyle(color = Color.Black),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = if (nombre.isNotEmpty() && !isValid) Color.Red else Pink,
                unfocusedBorderColor = if (nombre.isNotEmpty() && !isValid) Color.Red else Pink,
                errorBorderColor = Color.Red,
                cursorColor = Pink,
            ),
            singleLine = true,
            maxLines = 1
        )
        if (nombre.isNotEmpty() && !isValid) {
            Text(
                text = when {
                    isDni -> stringResource(R.string.dni)
                    isCorreo -> stringResource(R.string.correo)
                    else -> stringResource(R.string.nombre)
                },
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}

@Composable
fun PreguntaBoolean(enunciado: String, value: Boolean, onValueChange: (Boolean) -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .width(360.dp)
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)

        ) {
            Column {
                Text(
                    text = enunciado,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Pink

                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { onValueChange(true) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (value) Pink else Color.White
                        )
                    ) {
                        Text(
                            if (!enunciado.contains("casa"))stringResource(R.string.si) else stringResource(R.string.casa),
                            color = if (value) Color.White else Color.Black,
                            style = TextStyle(color = Color.Black)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = { onValueChange(false) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!value) Pink else Color.White
                        )
                    ) {
                        Text(
                           if(!enunciado.contains("casa"))stringResource(R.string.no) else stringResource(R.string.apartamento),
                            color = if (!value) Color.White else Color.Black,
                            style = TextStyle(color = Color.Black)
                        )
                    }
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreguntaTiempo(enunciado: String, seleccionHora: String, onHoraSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(enunciado)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = seleccionHora,
                isError = seleccionHora.isEmpty(),
                onValueChange = {},
                label = { Text("Seleccionar horas") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Pink,
                unfocusedBorderColor = Pink,
                cursorColor = Pink
            )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                (1..24).forEach { hora ->
                    val horaFormateada = String.format("%02d", hora)
                    DropdownMenuItem(
                        text = { Text(horaFormateada) },
                        onClick = {
                            onHoraSelected(horaFormateada)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}