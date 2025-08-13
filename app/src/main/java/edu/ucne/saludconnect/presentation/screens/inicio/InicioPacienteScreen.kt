package edu.ucne.saludconnect.presentation.screens.inicio

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.saludconnect.R


@Composable
fun InicioPacienteScreen(
    nombre: String,
    onAbrirProximaCita: () -> Unit = {},
    onAbrirHistorial: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        // AppBar simple centrado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Inicio", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(Modifier.height(24.dp))

        // Hola, {Nombre}
        Text(
            text = "Hola, $nombre",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(24.dp))

        // Próximas citas
        Text(
            text = "Próximas citas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))

        AppointmentCard(
            hora = "10:00 AM",
            titulo = "Consulta con el Dr. Martínez",
            sub = "Clínica San Lucas",
            imagen = R.drawable.doctora3,
            onClick = onAbrirProximaCita
        )

        Spacer(Modifier.height(28.dp))

        // Historial de citas
        Text(
            text = "Historial de citas",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))

        AppointmentCard(
            hora = null, // en historial no mostramos hora en el mock
            titulo = "Consulta con la Dra. García",
            sub = "Clínica San Lucas",
            imagen = R.drawable.doctora2, // usa tu recurso real
            onClick = onAbrirHistorial
        )

        Spacer(Modifier.height(24.dp))
    }
}

@Composable
private fun AppointmentCard(
    hora: String?,
    titulo: String,
    sub: String,
    @DrawableRes imagen: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable { onClick() }
            .background(Color(0xFFF7F8FA))
            .padding(14.dp)
    ) {
        // Columna de texto
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp)
        ) {
            if (hora != null) {
                Text(hora, color = Color(0xFF6A7C8A), fontSize = 14.sp)
                Spacer(Modifier.height(2.dp))
            }
            Text(titulo, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(Modifier.height(6.dp))
            Text(sub, color = Color(0xFF6A7C8A), fontSize = 14.sp)
        }

        // Imagen (tarjeta a la derecha)
        Image(
            painter = painterResource(id = imagen),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
    }
}
