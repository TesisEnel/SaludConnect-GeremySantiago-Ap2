package edu.ucne.saludconnect.presentation.screens.registros.registro_medicos

data class RegistroMedicoState(
    val nombre: String = "",
    val apellido: String = "",
    val licencia: String = "",
    val especialidad: String = "",
    val telefono: String = "",
    val correo: String = "",
    val hospital: String = "",
    val bio: String = "",

    val mensajeError: String? = null,
    val mensajeExito: String? = null,
    val isSaving: Boolean = false
)
