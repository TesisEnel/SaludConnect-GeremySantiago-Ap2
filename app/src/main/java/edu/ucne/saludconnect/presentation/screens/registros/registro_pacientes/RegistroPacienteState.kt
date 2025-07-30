package edu.ucne.saludconnect.presentation.screens.registros.registro_pacientes

data class RegistroPacienteState(
    val nombre: String = "",
    val apellido: String = "",
    val cedula: String = "",
    val edad: String = "",
    val telefono: String = "",
    val correo: String = "",
    val direccion: String = "",
    val alergias: String = "",
    val medicamentos: String = "",
    val condiciones: String = "",
    val mensajeError: String? = null,
    val mensajeExito: String? = null
)
