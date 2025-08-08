package edu.ucne.saludconnect.presentation.screens.menu


import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
import androidx.navigation.NavHostController
import edu.ucne.saludconnect.presentation.screens.perfiles.PacienteDashboardScreen


@Composable
fun MenuScreen(
    pacienteId: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    // Definimos un NavController local para el NavHost de las pestañas
    val nestedNavController = rememberNavController()

    val items = listOf(
        BottomNavItem("Inicio", Icons.Default.Home, "home"),
        BottomNavItem("Citas", Icons.Default.DateRange, "appointments"),
        BottomNavItem("Perfil", Icons.Default.Person, "dashboard")
    )

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
                                popUpTo(nestedNavController.graph.startDestinationId) {
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
            startDestination = "pacientedashboard",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                Text("Pantalla Inicio", Modifier.padding(24.dp))
            }
            composable("appointments") {
                Text("Citas (por implementar)", Modifier.padding(24.dp))
            }
            // Aquí, le pasamos el navController del HomeNavHost (el que recibimos como parámetro)
            // para que pueda navegar fuera de MenuScreen.
            composable("pacientedashboard") {
                PacienteDashboardScreen(navController = navController, pacienteId = pacienteId)
            }
        }
    }
}

