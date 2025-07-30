package edu.ucne.saludconnect.presentation.screens.perfiles

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun DashboardScreen(
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
                .padding(24.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text("Perfil del Paciente", fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            Text("Nombre: ${it.nombre}")
            Text("Cédula: ${it.cedula}")
            Text("Edad: ${it.edad}")
            Text("Teléfono: ${it.telefono}")
            Text("Correo: ${it.correo}")
            Text("Dirección: ${it.direccion}")
            Text("Alergias: ${it.alergias}")
            Text("Medicamentos: ${it.medicamentos}")
            Text("Condiciones médicas: ${it.condiciones}")

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                navController.navigate("editar_paciente/${it.id}")
            }) {
                Text("Editar Perfil")
            }

            Button(onClick = { navController.popBackStack() }) {
                Text("Cerrar sesión")
            }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}
