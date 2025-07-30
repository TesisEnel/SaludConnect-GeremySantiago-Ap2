package edu.ucne.saludconnect.presentation.screens.registros.registro_pacientes

sealed class RegistroPacienteEvent {
    data class NombreChanged(val value: String) : RegistroPacienteEvent()
    data class ApellidoChanged(val value: String) : RegistroPacienteEvent()
    data class CedulaChanged(val value: String) : RegistroPacienteEvent()
    data class EdadChanged(val value: String) : RegistroPacienteEvent()
    data class TelefonoChanged(val value: String) : RegistroPacienteEvent()
    data class CorreoChanged(val value: String) : RegistroPacienteEvent()
    data class DireccionChanged(val value: String) : RegistroPacienteEvent()
    data class AlergiasChanged(val value: String) : RegistroPacienteEvent()
    data class MedicamentosChanged(val value: String) : RegistroPacienteEvent()
    data class CondicionesChanged(val value: String) : RegistroPacienteEvent()
    object Registrar : RegistroPacienteEvent()
}
