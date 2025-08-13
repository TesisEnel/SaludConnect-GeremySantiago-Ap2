package edu.ucne.saludconnect.presentation.screens.menu


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import edu.ucne.saludconnect.presentation.screens.citas.NuevaCitaScreen
import edu.ucne.saludconnect.presentation.screens.citas.CitasMedicoScreen
import edu.ucne.saludconnect.presentation.screens.inicio.InicioHomeRoute
import edu.ucne.saludconnect.presentation.screens.perfiles.DoctorDashboardScreen
import edu.ucne.saludconnect.presentation.screens.perfiles.PacienteDashboardScreen


@Composable
fun MenuScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    usuarioId: Int,
    esDoctor: Boolean
) {
    // Controlador LOCAL para las pestañas del menú
    val nestedNavController = rememberNavController()

    val items = if (esDoctor) {
        listOf(
            BottomNavItem("Inicio", Icons.Default.Home, "home"),
            BottomNavItem("Citas", Icons.Default.DateRange, "cita_medico"),
            BottomNavItem("Perfil", Icons.Default.Person, "doctor_dashboard")
        )
    } else {
        listOf(
            BottomNavItem("Inicio", Icons.Default.Home, "home"),
            BottomNavItem("Citas", Icons.Default.DateRange, "nueva_cita"),
            BottomNavItem("Perfil", Icons.Default.Person, "paciente_dashboard")
        )
    }

    // ruta actual del grafo interno
    val backStackEntry by nestedNavController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            nestedNavController.navigate(item.route) {
                                // Seguridad: vuelve al start del grafo interno sin recrear todo
                                val startId = nestedNavController.graph.findStartDestination().id
                                popUpTo(startId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = nestedNavController, // Usa el NavController local
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                InicioHomeRoute(
                    usuarioId = usuarioId,
                    esDoctor = esDoctor
                )
            }
            composable("nueva_cita") {
                NuevaCitaScreen(navController = navController)
            }
            composable("cita_medico") {
                CitasMedicoScreen(navController = navController)
            }
            // Perfil según el rol. OJO: aquí pasamos el GLOBAL para navegar a flows fuera del menú
            // para que pueda navegar fuera de MenuScreen.
            composable("paciente_dashboard") {
                PacienteDashboardScreen(
                    navController = navController,
                    pacienteId = usuarioId
                )
            }
            composable("doctor_dashboard") {
                DoctorDashboardScreen(
                    navController = navController,
                    doctorId = usuarioId
                )
            }
        }
    }
}

