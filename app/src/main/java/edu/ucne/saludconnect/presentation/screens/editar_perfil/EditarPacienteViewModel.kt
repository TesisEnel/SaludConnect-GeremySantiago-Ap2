package edu.ucne.saludconnect.presentation.screens.editar_perfil


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import edu.ucne.saludconnect.data.local.entity.PacienteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.saludconnect.data.local.dao.PacienteDao
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class EditarPacienteViewModel @Inject constructor(
    private val pacienteDao: PacienteDao
) : ViewModel() {

    private val _state = mutableStateOf(EditarPacienteState())
    val state: State<EditarPacienteState> = _state

    fun cargarPaciente(id: Int) {
        viewModelScope.launch {
            pacienteDao.obtenerPacientePorId(id)?.let {
                _state.value = EditarPacienteState(
                    id = it.id,
                    nombre = it.nombre,
                    apellido = it.apellido,
                    cedula = it.cedula,
                    edad = it.edad,
                    telefono = it.telefono,
                    correo = it.correo,
                    direccion = it.direccion,
                    alergias = it.alergias,
                    medicamentos = it.medicamentos,
                    condiciones = it.condiciones
                )
            }
        }
    }

    fun onEvent(event: EditarPacienteEvent) {
        when (event) {
            is EditarPacienteEvent.NombreChanged -> {
                _state.value = _state.value.copy(nombre = event.value)
            }
            is EditarPacienteEvent.ApellidoChanged -> {
                _state.value = _state.value.copy(apellido = event.value)
            }
            is EditarPacienteEvent.EdadChanged -> {
                _state.value = _state.value.copy(edad = event.value)
            }
            is EditarPacienteEvent.TelefonoChanged -> {
                _state.value = _state.value.copy(telefono = event.value)
            }
            is EditarPacienteEvent.CorreoChanged -> {
                _state.value = _state.value.copy(correo = event.value)
            }
            is EditarPacienteEvent.DireccionChanged -> {
                _state.value = _state.value.copy(direccion = event.value)
            }
            is EditarPacienteEvent.AlergiasChanged -> {
                _state.value = _state.value.copy(alergias = event.value)
            }
            is EditarPacienteEvent.MedicamentosChanged -> {
                _state.value = _state.value.copy(medicamentos = event.value)
            }
            is EditarPacienteEvent.CondicionesChanged -> {
                _state.value = _state.value.copy(condiciones = event.value)
            }
            is EditarPacienteEvent.GuardarCambios -> {
                viewModelScope.launch {
                    pacienteDao.actualizarPaciente(
                        PacienteEntity(
                            id = _state.value.id,
                            nombre = _state.value.nombre,
                            apellido = _state.value.apellido,
                            edad = _state.value.edad,
                            telefono = _state.value.telefono,
                            correo = _state.value.correo,
                            direccion = _state.value.direccion,
                            cedula = _state.value.cedula, // opcional si no se edita
                            alergias = _state.value.alergias,
                            medicamentos = _state.value.medicamentos,
                            condiciones = _state.value.condiciones
                        )
                    )
                }
            }
        }
    }
}
