package edu.ucne.saludconnect.presentation.screens.registros.registro_medicos

data class RegistroMedicoState(
    val nombre: String = "",
    val apellido: String = "",
    val cedula: String = "",
    val especialidad: String = "",
    val telefono: String = "",
    val correo: String = "",
    val hospital: String = "",
    val mensajeError: String? = null,
    val mensajeExito: String? = null,
    val isSaving: Boolean = false
)
