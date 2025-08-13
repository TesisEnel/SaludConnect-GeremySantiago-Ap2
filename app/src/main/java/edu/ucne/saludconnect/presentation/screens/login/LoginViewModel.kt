package edu.ucne.saludconnect.presentation.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.saludconnect.data.local.dao.PacienteDao
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import edu.ucne.saludconnect.data.local.dao.DoctorDao
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val pacienteDao: PacienteDao,
    private val doctorDao: DoctorDao
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.CedulaChanged -> {
                _state.value = _state.value.copy(cedula = event.value)
            }
            is LoginEvent.TelefonoChanged -> {
                _state.value = _state.value.copy(telefono = event.value)
            }
            is LoginEvent.IniciarSesion -> {
                val s = _state.value

                if (s.cedula.isBlank() || s.telefono.isBlank()) {
                    _state.value = s.copy(mensajeError = "Cédula y teléfono son obligatorios.")
                    return
                }

                viewModelScope.launch {
                    val cedulaLong = s.cedula.toLongOrNull()
                    if (cedulaLong == null) {
                        _state.value = s.copy(mensajeError = "La cédula debe ser un número válido.")
                        return@launch
                    }

                    // Buscar paciente
                    val paciente = pacienteDao.obtenerPacientes().find {
                        it.cedula == cedulaLong && it.telefono == s.telefono
                    }

                    if (paciente != null) {
                        event.navController.navigate("menu_patient/${paciente.id}") {
                            popUpTo("login") { inclusive = true }
                        }
                        return@launch
                    }

                    // Si no es paciente, buscar médico
                    val doctor = doctorDao.getAll().find {
                        it.cedula.toLongOrNull() == cedulaLong && it.telefono == s.telefono
                    }

                    if (doctor != null) {
                        event.navController.navigate("menu_doctor/${doctor.id}") {
                            popUpTo("login") { inclusive = true }
                        }
                        return@launch
                    }

                    // Si no es ninguno
                    _state.value = s.copy(mensajeError = "Credenciales incorrectas.")
                }
            }
        }
    }

    fun limpiarMensajeExito() {
        _state.value = _state.value.copy(mensajeExito = null)
    }
}
