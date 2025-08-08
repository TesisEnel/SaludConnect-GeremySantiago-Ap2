package edu.ucne.saludconnect.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import edu.ucne.saludconnect.presentation.screens.pruebas.Destinos
import edu.ucne.saludconnect.presentation.screens.registros.registro_medicos.RegistroMedicoScreen
import edu.ucne.saludconnect.presentation.screens.registros.registro_pacientes.RegistroPacienteScreen

@Composable
fun HomeNavHost(
    navController: NavHostController
) {

    LaunchedEffect(Unit) {
        listOf(
            Destinos.Start.ruta,
            Destinos.RegistroPaciente.ruta,
            Destinos.RegistroDoctor.ruta,
            Destinos.Login.ruta,
            Destinos.Menu.ruta,
            Destinos.Dashboard.ruta,
            Destinos.EditarPaciente.ruta
        ).forEach { ruta ->
            Log.d("NAV_DESTINOS", "Destino: $ruta")
        }
    }

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


        composable("menu/{pacienteId}") { backStackEntry ->
            val pacienteId = backStackEntry.arguments?.getString("pacienteId")?.toIntOrNull()
            pacienteId?.let {
                MenuScreen(navController = navController, pacienteId = it)
            }
        }



        /*
        composable("editar_paciente/{id}") { backStackEntry ->
            val pacienteId = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            pacienteId?.let {
                EditarPacienteScreen(navController, pacienteId = it)
            }
        }
         */
        composable("dashboard/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            id?.let {
                PacienteDashboardScreen(navController, pacienteId = it)
            }
        }

        composable("doctor_dashboard/{id}") { backStackEntry ->
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
