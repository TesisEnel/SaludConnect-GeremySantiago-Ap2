package edu.ucne.saludconnect.presentation.screens.editar_perfil

sealed class EditarPacienteEvent {
    data class NombreChanged(val value: String) : EditarPacienteEvent()
    data class ApellidoChanged(val value: String) : EditarPacienteEvent()
    data class EdadChanged(val value: String) : EditarPacienteEvent()
    data class TelefonoChanged(val value: String) : EditarPacienteEvent()
    data class CorreoChanged(val value: String) : EditarPacienteEvent()
    data class DireccionChanged(val value: String) : EditarPacienteEvent()
    data class AlergiasChanged(val value: String) : EditarPacienteEvent()
    data class MedicamentosChanged(val value: String) : EditarPacienteEvent()
    data class CondicionesChanged(val value: String) : EditarPacienteEvent()
    object GuardarCambios : EditarPacienteEvent()
}
