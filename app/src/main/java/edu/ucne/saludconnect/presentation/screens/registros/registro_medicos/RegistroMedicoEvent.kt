package edu.ucne.saludconnect.presentation.screens.registros.registro_medicos

sealed class RegistroMedicoEvent {
    data class NombreChanged(val v: String): RegistroMedicoEvent()
    data class ApellidoChanged(val v: String): RegistroMedicoEvent()
    data class CedulaChanged(val v: String): RegistroMedicoEvent()
    data class EspecialidadChanged(val v: String): RegistroMedicoEvent()
    data class TelefonoChanged(val v: String): RegistroMedicoEvent()
    data class CorreoChanged(val v: String): RegistroMedicoEvent()
    data class HospitalChanged(val v: String): RegistroMedicoEvent()
    data object Registrar: RegistroMedicoEvent()
    data object ClearSuccess: RegistroMedicoEvent()
}
