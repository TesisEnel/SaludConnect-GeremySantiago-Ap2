package edu.ucne.saludconnect.presentation.screens.registros.registro_medicos

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.saludconnect.data.local.dao.DoctorDao
import edu.ucne.saludconnect.data.local.entity.DoctorEntity
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegistroMedicoViewModel @Inject constructor(
    private val doctorDao: DoctorDao
) : ViewModel() {

    private val _state = mutableStateOf(RegistroMedicoState())
    val state: State<RegistroMedicoState> = _state

    fun onEvent(e: RegistroMedicoEvent) {
        when (e) {
            is RegistroMedicoEvent.NombreChanged     -> _state.value = state.value.copy(nombre = e.v)
            is RegistroMedicoEvent.ApellidoChanged   -> _state.value = state.value.copy(apellido = e.v)
            is RegistroMedicoEvent.CedulaChanged   -> _state.value = state.value.copy(cedula = e.v)
            is RegistroMedicoEvent.EspecialidadChanged -> _state.value = state.value.copy(especialidad = e.v)
            is RegistroMedicoEvent.TelefonoChanged   -> _state.value = state.value.copy(telefono = e.v)
            is RegistroMedicoEvent.CorreoChanged     -> _state.value = state.value.copy(correo = e.v)
            is RegistroMedicoEvent.HospitalChanged   -> _state.value = state.value.copy(hospital = e.v)

            RegistroMedicoEvent.Registrar -> registrar()
            RegistroMedicoEvent.ClearSuccess -> _state.value = state.value.copy(mensajeExito = null)
        }
    }

    private fun registrar() = viewModelScope.launch {
        val s = state.value

        // Validaciones básicas
        if (s.nombre.isBlank() || s.apellido.isBlank() || s.cedula.isBlank()
            || s.especialidad.isBlank() || s.telefono.isBlank()
            || s.correo.isBlank() || s.hospital.isBlank()
        ) {
            _state.value = s.copy(mensajeError = "Completa todos los campos obligatorios.")
            return@launch
        }
        /*
        if (!Patterns.EMAIL_ADDRESS.matcher(s.correo).matches()) {
            _state.value = s.copy(mensajeError = "Correo inválido.")
            return@launch
        }
         */
        if (doctorDao.getByCedula(s.cedula) != null) {
            _state.value = s.copy(mensajeError = "La cedula ya existe.")
            return@launch
        }

        _state.value = s.copy(isSaving = true, mensajeError = null)

        doctorDao.insert(
            DoctorEntity(
                nombre = s.nombre,
                apellido = s.apellido,
                cedula = s.cedula,
                especialidad = s.especialidad,
                telefono = s.telefono,
                correo = s.correo,
                hospital = s.hospital,
            )
        )

        // Limpiar formulario + mensaje
        _state.value = RegistroMedicoState(mensajeExito = "Médico registrado exitosamente")
    }
}
