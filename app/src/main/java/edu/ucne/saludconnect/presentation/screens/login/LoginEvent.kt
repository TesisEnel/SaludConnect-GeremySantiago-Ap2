package edu.ucne.saludconnect.presentation.screens.login

import androidx.navigation.NavController

sealed class LoginEvent {
    data class CedulaChanged(val value: String) : LoginEvent()
    data class TelefonoChanged(val value: String) : LoginEvent()
    data class IniciarSesion(val navController: NavController) : LoginEvent()
}
