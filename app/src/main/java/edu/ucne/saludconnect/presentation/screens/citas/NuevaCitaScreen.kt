package edu.ucne.saludconnect.presentation.screens.citas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuevaCitaScreen(
    navController: NavController
) {
    val especialidades = listOf("Cardiología", "Dermatología", "Gastroenterología", "Neurología", "Pediatría", "Psiquiatría")
    val doctores = listOf(
        DoctorUi("Dr. Alejandro Vargas", "Cardiología"),
        DoctorUi("Dra. Sofia Ramirez", "Cardiología")
    )

    var searchEspecialidad by remember { mutableStateOf("") }
    var searchDoctor by remember { mutableStateOf("") }

    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(it))
    }

    val horasDisponibles = listOf("9:00 AM", "10:00 AM", "11:00 AM")
    var horaSeleccionada by remember { mutableStateOf("10:00 AM") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("Selecciona una especialidad", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        OutlinedTextField(
            value = searchEspecialidad,
            onValueChange = { searchEspecialidad = it },
            placeholder = { Text("Buscar especialidad") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 8.dp)) {
            items(especialidades.filter { it.contains(searchEspecialidad, ignoreCase = true) }) { especialidad ->
                Surface(
                    color = Color(0xFFF1F3F5),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .clickable { /* Seleccionar especialidad */ }
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        especialidad,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.Black
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Selecciona un doctor", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        OutlinedTextField(
            value = searchDoctor,
            onValueChange = { searchDoctor = it },
            placeholder = { Text("Buscar médico") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.padding(top = 8.dp)) {
            doctores.filter { it.nombre.contains(searchDoctor, ignoreCase = true) }.forEach { doctor ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { /* Seleccionar doctor */ }
                        .padding(8.dp)
                ) {
                    Column {
                        Text(doctor.nombre, fontWeight = FontWeight.Bold)
                        Text(doctor.especialidad, color = Color.Gray)
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Selecciona fecha y hora", fontWeight = FontWeight.Bold, fontSize = 18.sp)

        DatePicker(state = datePickerState)

        Spacer(Modifier.height(8.dp))
        Text("Fecha seleccionada: ${selectedDate ?: "Ninguna"}", color = Color.Gray)

        Spacer(Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            horasDisponibles.forEach { hora ->
                val seleccionado = hora == horaSeleccionada
                Surface(
                    color = if (seleccionado) Color.Blue else Color(0xFFF1F3F5),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .clickable { horaSeleccionada = hora }
                ) {
                    Text(
                        hora,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = if (seleccionado) Color.White else Color.Black
                    )
                }
            }
        }

        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate("cita_confirmada")
                // Guardar cita
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text("Reservar Cita", fontSize = 16.sp)
        }
    }
}

data class DoctorUi(
    val nombre: String,
    val especialidad: String
)
