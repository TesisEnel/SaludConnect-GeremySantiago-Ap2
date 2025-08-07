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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import edu.ucne.saludconnect.presentation.screens.editar_perfil.EditarPacienteScreen
import edu.ucne.saludconnect.presentation.screens.perfiles.DashboardScreen


@Composable
fun MenuScreen(
    pacienteId: Int,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavItem("Inicio", Icons.Default.Home, "home"),
        BottomNavItem("Citas", Icons.Default.DateRange, "appointments"),
        BottomNavItem("Perfil", Icons.Default.Person, "dashboard")
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = navController.currentDestination?.route == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo("menu/$pacienteId") { inclusive = false }
                                launchSingleTop = true
                            }
                        }
                    )
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                Text("Pantalla Inicio", Modifier.padding(24.dp))
            }
            composable("appointments") {
                Text("Citas (por implementar)", Modifier.padding(24.dp))
            }
            composable("dashboard") {
                DashboardScreen(navController = navController, pacienteId = pacienteId)
            }
            /*
            composable(
                route = "editar_paciente/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                id?.let {
                    EditarPacienteScreen(navController, pacienteId = it)
                }
            }

             */
        }
    }
}

