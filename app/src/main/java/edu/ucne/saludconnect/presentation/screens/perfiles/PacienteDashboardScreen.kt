package edu.ucne.saludconnect.presentation.screens.perfiles

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.saludconnect.R

@Composable
fun PacienteDashboardScreen(
    navController: NavController,
    pacienteId: Int,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val paciente = viewModel.paciente.value

    LaunchedEffect(pacienteId) {
        viewModel.cargarPaciente(pacienteId)
    }

    paciente?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Perfil del Paciente",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Imagen de perfil
            Image(
                painter = painterResource(id = R.drawable.persona),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.LightGray, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Datos principales
            Text("${it.nombre} ${it.apellido}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("ID: ${it.id}", color = Color.Gray)
            Text("Edad: ${it.edad}  |  Teléfono: ${it.telefono}", color = Color.Gray)

            Spacer(modifier = Modifier.height(12.dp))


            // Botón editar
            Button(
                onClick = { navController.navigate("editar_paciente/${it.id}") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEEEEE)),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Editar", color = Color.Black, fontWeight = FontWeight.Medium)
            }


            Spacer(modifier = Modifier.height(24.dp))

            // Sección de información personal
            Text("Información Personal", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            InfoItem("Nombre", "${it.nombre} ${it.apellido}")
            InfoItem("Cédula", it.cedula.toString())
            InfoItem("Edad", it.edad.toString())
            InfoItem("Teléfono", it.telefono)
            InfoItem("Correo Electrónico", it.correo)
            InfoItem("Dirección", it.direccion)

            Spacer(modifier = Modifier.height(24.dp))

            // Sección historial médico
            Text("Historial Médico", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            InfoItem("Alergias", it.alergias)
            InfoItem("Medicamentos", it.medicamentos)
            InfoItem("Condiciones médicas", it.condiciones)

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0C30B4)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Text("Cerrar sesión", color = Color.White)
            }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun InfoItem(
    label: String,
    value: String
) {
    /*Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 4.dp)) {
        Text(label, fontWeight = FontWeight.Medium, color = Color(0xFF607D8B))
        Text(value, modifier = Modifier.padding(start = 8.dp))
    }

     */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(label, style = MaterialTheme.typography.titleMedium)
            Text(value, color = Color(0xFF6A7C8A), fontSize = 18.sp)
        }
    }
}

