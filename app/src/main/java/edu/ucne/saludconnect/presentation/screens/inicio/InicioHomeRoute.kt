package edu.ucne.saludconnect.presentation.screens.inicio

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun InicioHomeRoute(
    usuarioId: Int,
    esDoctor: Boolean,
    vm: InicioHomeViewModel = hiltViewModel()
) {
    // Dispara la carga cuando cambie el usuario/rol
    LaunchedEffect(usuarioId, esDoctor) {
        vm.cargarNombre(usuarioId, esDoctor)
    }

    // Nombre reactivo
    val nombre = vm.nombre ?: "Usuario"

    if (esDoctor) {
        InicioDoctorRoute(nombre = nombre)
    } else {
        InicioPacienteScreen(nombre = nombre)
    }
}
