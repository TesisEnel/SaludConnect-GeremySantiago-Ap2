package edu.ucne.saludconnect.presentation.screens.citas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CitasMedicoScreen(
    navController: NavController
) {
    val citasHoy = listOf(
        CitaUi("10:00 AM - 10:30 AM", "Consulta general"),
        CitaUi("11:00 AM - 11:30 AM", "Consulta general"),
        CitaUi("12:00 PM - 12:30 PM", "Consulta general")
    )

    val citasManana = listOf(
        CitaUi("10:00 AM - 10:30 AM", "Consulta general"),
        CitaUi("11:00 AM - 11:30 AM", "Consulta general"),
        CitaUi("12:00 PM - 12:30 PM", "Consulta general")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Citas del Médico",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        // Sección Hoy
        Text("Hoy", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        citasHoy.forEach { cita -> CitaItem(cita) }

        Spacer(Modifier.height(16.dp))

        // Sección Mañana
        Text("Mañana", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        citasManana.forEach { cita -> CitaItem(cita) }
    }
}

@Composable
fun CitaItem(cita: CitaUi) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = Color(0xFFF8F8F8),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(cita.hora, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(cita.tipo, color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}

data class CitaUi(
    val hora: String,
    val tipo: String
)
