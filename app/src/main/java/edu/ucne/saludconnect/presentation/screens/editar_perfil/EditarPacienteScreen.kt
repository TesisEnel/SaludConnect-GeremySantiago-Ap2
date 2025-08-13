package edu.ucne.saludconnect.presentation.screens.editar_perfil


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import edu.ucne.saludconnect.presentation.screens.registros.registro_pacientes.InputField


@Composable
fun EditarPacienteScreen(
    navController: NavController,
    pacienteId: Int,
    viewModel: EditarPacienteViewModel = hiltViewModel()
) {
    val state by viewModel.state

    LaunchedEffect(pacienteId) {
        viewModel.cargarPaciente(pacienteId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Editar Perfil", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        InputField("Nombre", state.nombre) {
            viewModel.onEvent(EditarPacienteEvent.NombreChanged(it))
        }
        InputField("Apellido", state.apellido) {
            viewModel.onEvent(EditarPacienteEvent.ApellidoChanged(it))
        }
        InputField("Edad", state.edad) {
            viewModel.onEvent(EditarPacienteEvent.EdadChanged(it))
        }
        InputField("Teléfono", state.telefono) {
            viewModel.onEvent(EditarPacienteEvent.TelefonoChanged(it))
        }
        InputField("Correo", state.correo) {
            viewModel.onEvent(EditarPacienteEvent.CorreoChanged(it))
        }
        InputField("Dirección", state.direccion) {
            viewModel.onEvent(EditarPacienteEvent.DireccionChanged(it))
        }
        InputField("Alergia", state.alergias) {
            viewModel.onEvent(EditarPacienteEvent.AlergiasChanged(it))
        }
        InputField("Medicamentos", state.medicamentos) {
            viewModel.onEvent(EditarPacienteEvent.MedicamentosChanged(it))
        }
        InputField("Condiciones", state.condiciones) {
            viewModel.onEvent(EditarPacienteEvent.CondicionesChanged(it))
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            viewModel.onEvent(EditarPacienteEvent.GuardarCambios)
            navController.popBackStack() // Regresar al Dashboard
        }) {
            Text("Guardar Cambios")
        }
    }
}
