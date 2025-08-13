package edu.ucne.saludconnect.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import edu.ucne.saludconnect.presentation.screens.editar_perfil.EditarPacienteScreen
import edu.ucne.saludconnect.presentation.screens.home.HomeScreen
import edu.ucne.saludconnect.presentation.screens.login.LoginScreen
import edu.ucne.saludconnect.presentation.screens.menu.MenuScreen
import edu.ucne.saludconnect.presentation.screens.perfiles.DoctorDashboardScreen
import edu.ucne.saludconnect.presentation.screens.perfiles.PacienteDashboardScreen
import edu.ucne.saludconnect.presentation.screens.registros.registro_medicos.RegistroMedicoScreen
import edu.ucne.saludconnect.presentation.screens.registros.registro_pacientes.RegistroPacienteScreen

@Composable
fun HomeNavHost(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = "start") {
        composable("start") {
            HomeScreen(navController)
        }
        composable("register_patient") {
            RegistroPacienteScreen(navController)
            // Pantalla de registro de paciente
        }
        composable("register_doctor") {
            RegistroMedicoScreen(navController)
            // Pantalla de registro de médico
        }
        composable("login") {
            LoginScreen(navController) // Asegúrate de tener LoginScreen definida
        }

        // Menú para paciente
        composable("menu_patient/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                MenuScreen(navController = navController, usuarioId = it, esDoctor = false)
            }
        }

        // Menú para médico
        composable("menu_doctor/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                MenuScreen(navController = navController, usuarioId = it, esDoctor = true)
            }
        }

        composable("pacientedashboard/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                PacienteDashboardScreen(navController, pacienteId = it)
            }
        }

        composable("doctordashboard/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                DoctorDashboardScreen(navController, doctorId = it)
            }
        }

        composable(
            route = "editar_paciente/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val pacienteId = backStackEntry.arguments?.getInt("id")
            pacienteId?.let {
                EditarPacienteScreen(navController, pacienteId = it)
            }
        }
    }
}
