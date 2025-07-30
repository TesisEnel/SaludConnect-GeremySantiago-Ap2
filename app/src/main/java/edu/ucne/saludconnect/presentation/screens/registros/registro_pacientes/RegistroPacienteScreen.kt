package edu.ucne.saludconnect.presentation.screens.registros.registro_pacientes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun RegistroPacienteScreen(
    navController: NavController,
    viewModel: RegistroPacienteViewModel = hiltViewModel()
) {
    val state by viewModel.state
    val snackbarHostState = remember { SnackbarHostState() }

    // Efecto para mostrar el snackbar cuando mensajeExito cambia
    LaunchedEffect(state.mensajeExito) {
        if (!state.mensajeExito.isNullOrEmpty()) {
            snackbarHostState.showSnackbar(state.mensajeExito!!)
            viewModel.limpiarMensajeExito() // 🧹 limpiar después
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            Text("Registro de Paciente", fontWeight = FontWeight.Bold, fontSize = 22.sp)

            Spacer(modifier = Modifier.height(16.dp))

            // Errores visibles en pantalla
            if (!state.mensajeError.isNullOrEmpty()) {
                Text(
                    text = state.mensajeError ?: "",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }

            // Campos del formulario
            InputField("Nombre completo", state.nombre) {
                viewModel.onEvent(RegistroPacienteEvent.NombreChanged(it))
            }
            InputField("Apellido completo", state.apellido) {
                viewModel.onEvent(RegistroPacienteEvent.ApellidoChanged(it))
            }
            InputField("Cédula", state.cedula) {
                viewModel.onEvent(RegistroPacienteEvent.CedulaChanged(it))
            }
            InputField("Edad", state.edad) {
                viewModel.onEvent(RegistroPacienteEvent.EdadChanged(it))
            }
            InputField("Teléfono", state.telefono) {
                viewModel.onEvent(RegistroPacienteEvent.TelefonoChanged(it))
            }
            InputField("Correo Electrónico", state.correo) {
                viewModel.onEvent(RegistroPacienteEvent.CorreoChanged(it))
            }
            InputField("Dirección", state.direccion) {
                viewModel.onEvent(RegistroPacienteEvent.DireccionChanged(it))
            }
            InputField("Alergias", state.alergias) {
                viewModel.onEvent(RegistroPacienteEvent.AlergiasChanged(it))
            }
            InputField("Medicamentos", state.medicamentos) {
                viewModel.onEvent(RegistroPacienteEvent.MedicamentosChanged(it))
            }
            InputField("Condiciones Médicas", state.condiciones) {
                viewModel.onEvent(RegistroPacienteEvent.CondicionesChanged(it))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    viewModel.onEvent(RegistroPacienteEvent.Registrar)
                }
            ) {
                Text("Registrar")
            }
        }
    }
}


@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}
