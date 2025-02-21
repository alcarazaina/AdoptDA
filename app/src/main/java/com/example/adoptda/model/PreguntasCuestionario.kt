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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreguntaTextField(enunciado: String, nombre: String, onNombreChange: (String) -> Unit) {
    Column {
            OutlinedTextField(
                value = nombre,
                onValueChange = onNombreChange,
                label = {
                    Text(enunciado,
                    style = TextStyle(color = Color.Black)
                ) },
                isError = nombre.isEmpty(),
                textStyle = TextStyle(color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.DarkGray,
                    cursorColor = Color.Black
                )
            )
    }
}

@Composable
fun PreguntaBoolean(enunciado: String, value: Boolean, onValueChange: (Boolean) -> Unit) {
    Column {
        Box(
            modifier = Modifier
                .width(360.dp)
                .background(Color.White.copy(alpha = 0.6f), shape = RoundedCornerShape(12.dp))
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)

        ) {
            Column {
                Text(
                    text = enunciado,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color(0xFF005665)

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
                            containerColor = if (value) Color(0xFF005665) else Color.White
                        )
                    ) {
                        Text(
                            "yes",
                            color = if (value) Color.White else Color.Black,
                            style = TextStyle(color = Color.Black)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = { onValueChange(false) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (!value) Color(0xFF005665) else Color.White
                        )
                    ) {
                        Text(
                            "no",
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
                modifier = Modifier.menuAnchor()
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