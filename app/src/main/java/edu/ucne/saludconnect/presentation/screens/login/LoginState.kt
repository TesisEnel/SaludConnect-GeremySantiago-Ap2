package edu.ucne.saludconnect.presentation.screens.login

data class LoginState(
    val cedula: String = "",
    val telefono: String = "",
    val mensajeError: String? = null,
    val mensajeExito: String? = null
)
