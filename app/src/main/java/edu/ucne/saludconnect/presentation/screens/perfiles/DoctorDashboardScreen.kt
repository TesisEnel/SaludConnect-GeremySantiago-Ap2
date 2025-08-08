package edu.ucne.saludconnect.presentation.screens.perfiles


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.saludconnect.R

@Composable
fun DoctorDashboardScreen(
    navController: NavController,
    doctorId: Int,
    viewModel: DoctorDashboardViewModel = hiltViewModel()
) {
    val doctor = viewModel.doctor.value

    LaunchedEffect(doctorId) {
        viewModel.cargarDoctor(doctorId)
    }

    if (doctor == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título
        Text("Perfil", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        // Avatar
        Image(
            painter = painterResource(id = R.drawable.doctora2),
            contentDescription = "Foto de perfil",
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .border(2.dp, Color(0xFFE3E6EA), CircleShape)
        )

        Spacer(Modifier.height(16.dp))

        // Nombre + subtítulos (especialidad / hospital)
        Text("${doctor.nombre} ${doctor.apellido}", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(doctor.especialidad, color = Color(0xFF6A7C8A), fontSize = 18.sp)
        Text(doctor.hospital, color = Color(0xFF6A7C8A), fontSize = 18.sp)

        Spacer(Modifier.height(28.dp))

        // Sección de contacto
        Text(
            "Información de contacto",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Black),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        ContactItem(
            icon = Icons.Outlined.Phone,
            label = "Teléfono",
            value = doctor.telefono
        )
        Spacer(Modifier.height(12.dp))
        ContactItem(
            icon = Icons.Outlined.Email,
            label = "Correo electrónico",
            value = doctor.correo
        )

        Spacer(Modifier.height(28.dp))

        // (Opcional) Botón atrás o cerrar sesión, según tu flujo
        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Volver")
        }
    }
}

@Composable
private fun ContactItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // “Icon container” cuadrado con esquinas redondeadas como el mock
        Surface(
            color = Color(0xFFF1F3F5),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.size(56.dp)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Icon(icon, contentDescription = null, tint = Color.Black.copy(alpha = 0.75f))
            }
        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(label, style = MaterialTheme.typography.titleMedium)
            Text(value, color = Color(0xFF6A7C8A), fontSize = 18.sp)
        }
    }
}
