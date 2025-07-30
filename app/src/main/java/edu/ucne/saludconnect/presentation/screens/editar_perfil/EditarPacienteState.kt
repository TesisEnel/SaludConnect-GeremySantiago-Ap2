package edu.ucne.saludconnect.presentation.screens.editar_perfil

data class EditarPacienteState(
    val id: Int = 0,
    val nombre: String = "",
    val apellido: String = "",
    val cedula: Long = 0L,
    val edad: String = "",
    val telefono: String = "",
    val correo: String = "",
    val direccion: String = "",
    val alergias: String = "",
    val medicamentos: String = "",
    val condiciones: String = ""
)
