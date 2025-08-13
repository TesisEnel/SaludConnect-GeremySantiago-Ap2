package edu.ucne.saludconnect.presentation.screens.registros.registro_medicos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroMedicoScreen(
    navController: NavController,
    viewModel: RegistroMedicoViewModel = hiltViewModel()
) {
    val state by viewModel.state
    val snackbarHostState = remember { SnackbarHostState() }

    // Mostrar snackbar al éxito
    LaunchedEffect(state.mensajeExito) {
        state.mensajeExito?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.onEvent(RegistroMedicoEvent.ClearSuccess)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Registro de Médico") })
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Error visible
            if (!state.mensajeError.isNullOrEmpty()) {
                Text(
                    text = state.mensajeError!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            // Campos
            OutField("Nombre", state.nombre) {
                viewModel.onEvent(RegistroMedicoEvent.NombreChanged(it)) }
            OutField("Apellido", state.apellido) {
                viewModel.onEvent(RegistroMedicoEvent.ApellidoChanged(it)) }
            OutField("Cédula", state.cedula) {
                viewModel.onEvent(RegistroMedicoEvent.CedulaChanged(it)) }
            OutField("Especialidad", state.especialidad) {
                viewModel.onEvent(RegistroMedicoEvent.EspecialidadChanged(it)) }
            OutField("Teléfono", state.telefono) {
                viewModel.onEvent(RegistroMedicoEvent.TelefonoChanged(it)) }
            OutField("Correo", state.correo) {
                viewModel.onEvent(RegistroMedicoEvent.CorreoChanged(it)) }
            OutField("Hospital / Centro", state.hospital) {
                viewModel.onEvent(RegistroMedicoEvent.HospitalChanged(it)) }

            Spacer(Modifier.height(18.dp))

            Button(
                onClick = { viewModel.onEvent(RegistroMedicoEvent.Registrar) },
                enabled = !state.isSaving,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (state.isSaving) CircularProgressIndicator(Modifier.size(18.dp), strokeWidth = 2.dp)
                else Text("Registrar")
            }
        }
    }
}

@Composable
private fun OutField(label: String, value: String, onValue: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValue,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.dp)
    )
}
