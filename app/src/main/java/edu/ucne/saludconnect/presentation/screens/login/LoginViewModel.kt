package edu.ucne.saludconnect.presentation.screens.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.saludconnect.data.local.dao.PacienteDao
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val pacienteDao: PacienteDao
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

                    val pacientes = pacienteDao.obtenerPacientes()
                    val paciente = pacientes.find {
                        it.cedula == cedulaLong && it.telefono == s.telefono
                    }

                    if (paciente != null) {
                        // Ir a pantalla de perfil o dashboard
                        event.navController.navigate("dashboard/${paciente.id}")
                    } else {
                        _state.value = s.copy(mensajeError = "Credenciales incorrectas.")
                    }
                }
            }
        }
    }

    fun limpiarMensajeExito() {
        _state.value = _state.value.copy(mensajeExito = null)
    }
}
