package edu.ucne.saludconnect.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.saludconnect.presentation.screens.editar_perfil.EditarPacienteScreen
import edu.ucne.saludconnect.presentation.screens.home.HomeScreen
import edu.ucne.saludconnect.presentation.screens.login.LoginScreen
import edu.ucne.saludconnect.presentation.screens.perfiles.DashboardScreen
import edu.ucne.saludconnect.presentation.screens.registros.registro_pacientes.RegistroPacienteScreen

@Composable
fun HomeNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            HomeScreen(navController)
        }
        composable("register_patient") {
            RegistroPacienteScreen(navController)
            // Pantalla de registro de paciente
        }
        composable("register_doctor") {
            // Aquí irá la pantalla de registro de médico
        }
        composable("login") {
            LoginScreen(navController) // Asegúrate de tener LoginScreen definida
        }
        composable("dashboard/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                DashboardScreen(navController, pacienteId = it)
            }
        }
        composable("editar_paciente/{id}") { backStackEntry ->
            val pacienteId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            pacienteId?.let {
                EditarPacienteScreen(navController, pacienteId = it)
            }
        }

    }
}
