package edu.ucne.saludconnect.presentation.screens.inicio

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun InicioDoctorRoute(
    nombre: String,
    onCitaClick: (CitaUi) -> Unit = {}
) {
    // Datos de ejemplo sin imágenes
    val hoy = listOf(
        CitaUi("Sofia Ramirez", "10:00 AM"),
        CitaUi("Carlos Mendoza", "11:30 AM"),
        CitaUi("Isabella Torres", "2:00 PM")
    )

    val manana = listOf(
        CitaUi("Diego Rodriguez", "9:00 AM"),
        CitaUi("Valeria Castro", "10:30 AM")
    )

    // 👇 ahora le pasamos el nombre a la pantalla
    InicioDoctorScreen(
        citasHoy = hoy,
        citasManana = manana,
        onCitaClick = onCitaClick,
        nombre = nombre
    )
}

@Composable
fun InicioDoctorScreen(
    citasHoy: List<CitaUi> = emptyList(),
    citasManana: List<CitaUi> = emptyList(),
    onCitaClick: (CitaUi) -> Unit = {},
    nombre: String? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // AppBar centrado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Inicio", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(16.dp))

        // 👇 saludo con el nombre (si llega)
        if (!nombre.isNullOrBlank()) {
            Text(
                text = "Hola, $nombre",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(16.dp))
        }

        // ---- Hoy ----
        Text("Hoy", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        citasHoy.forEach { cita ->
            AppointmentRow(cita = cita, onClick = { onCitaClick(cita) })
            Spacer(Modifier.height(16.dp))
        }

        Spacer(Modifier.height(24.dp))

        // ---- Mañana ----
        Text("Mañana", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        citasManana.forEach { cita ->
            AppointmentRow(cita = cita, onClick = { onCitaClick(cita) })
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun AppointmentRow(
    cita: CitaUi,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(Modifier.weight(1f)) {
            Text(
                text = "Consulta con ${cita.paciente}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = cita.hora,
                fontSize = 18.sp,
                color = Color(0xFF6A7C8A) // gris azulado como en el mock
            )
        }
    }
}

// Modelo simple para la UI sin avatar
data class CitaUi(
    val paciente: String,
    val hora: String
)

