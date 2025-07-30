package edu.ucne.saludconnect.presentation.screens.registros.registro_pacientes

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import edu.ucne.saludconnect.data.local.entity.PacienteEntity
import edu.ucne.saludconnect.data.repository.PacienteRepository
import kotlinx.coroutines.launch

@HiltViewModel
class RegistroPacienteViewModel @Inject constructor(
    private val repository: PacienteRepository
) : ViewModel() {
    private val _state = mutableStateOf(RegistroPacienteState())
    val state: State<RegistroPacienteState> = _state

    fun onEvent(event: RegistroPacienteEvent) {
        when (event) {
            is RegistroPacienteEvent.NombreChanged -> {
                _state.value = _state.value.copy(nombre = event.value)
            }
            is RegistroPacienteEvent.ApellidoChanged -> {
                _state.value = _state.value.copy(apellido = event.value)
            }
            is RegistroPacienteEvent.CedulaChanged -> {
                _state.value = _state.value.copy(cedula = event.value)
            }
            is RegistroPacienteEvent.EdadChanged -> {
                _state.value = _state.value.copy(edad = event.value)
            }
            is RegistroPacienteEvent.TelefonoChanged -> {
                _state.value = _state.value.copy(telefono = event.value)
            }
            is RegistroPacienteEvent.CorreoChanged -> {
                _state.value = _state.value.copy(correo = event.value)
            }
            is RegistroPacienteEvent.DireccionChanged -> {
                _state.value = _state.value.copy(direccion = event.value)
            }
            is RegistroPacienteEvent.AlergiasChanged -> {
                _state.value = _state.value.copy(alergias = event.value)
            }
            is RegistroPacienteEvent.MedicamentosChanged -> {
                _state.value = _state.value.copy(medicamentos = event.value)
            }
            is RegistroPacienteEvent.CondicionesChanged -> {
                _state.value = _state.value.copy(condiciones = event.value)
            }
            is RegistroPacienteEvent.Registrar -> {
                // Aquí iría la lógica para guardar el paciente en Room o en backend
                val s = _state.value

                // Validaciones
                if (s.nombre.isBlank() || s.cedula.isBlank() || s.edad.isBlank() ||
                    s.telefono.isBlank() || s.correo.isBlank() || s.direccion.isBlank()) {
                    _state.value = s.copy(mensajeError = "Por favor completa todos los campos obligatorios.")
                    return
                }

                if (!s.edad.all { it.isDigit() }) {
                    _state.value = s.copy(mensajeError = "La edad debe ser un número válido.")
                    return
                }

                /*if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s.correo).matches()) {
                    _state.value = s.copy(mensajeError = "El correo no es válido.")
                    return
                }*/

                //si todo está correcto, limpiar el mensaje de error y guardar
                _state.value = s.copy(mensajeError = null)

                viewModelScope.launch {
                    val existe = repository.getPacientePorCedula(s.cedula.toLong())
                    if (existe != null) {
                        _state.value = s.copy(mensajeError = "Ya existe un paciente con esa cédula.")
                        return@launch
                    }

                    val paciente = PacienteEntity(
                        nombre = s.nombre,
                        apellido = s.apellido,
                        cedula = s.cedula.toLong(),
                        edad = s.edad,
                        telefono = s.telefono,
                        correo = s.correo,
                        direccion = s.direccion,
                        alergias = s.alergias,
                        medicamentos = s.medicamentos,
                        condiciones = s.condiciones
                    )

                    try {
                        repository.insertarPaciente(paciente)
                        limpiarMensajeExito()
                    } catch (e: Exception) {
                        _state.value = s.copy(mensajeError = "Error al registrar: ${e.localizedMessage}")
                    }

                }
            }
        }
    }

    fun limpiarMensajeExito() {
        _state.value = RegistroPacienteState(
            mensajeExito = "Paciente registrado exitosamente"
        )
    }

}
