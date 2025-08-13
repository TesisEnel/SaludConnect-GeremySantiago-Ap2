package edu.ucne.saludconnect.presentation.screens.inicio

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.saludconnect.data.local.dao.DoctorDao
import edu.ucne.saludconnect.data.local.dao.PacienteDao
import javax.inject.Inject

@HiltViewModel
class InicioHomeViewModel @Inject constructor(
    private val pacienteDao: PacienteDao,
    private val doctorDao: DoctorDao
) : ViewModel() {

    var nombre by mutableStateOf<String?>(null)
        private set

    fun cargarNombre(id: Int, esDoctor: Boolean) {
        viewModelScope.launch {
            nombre = if (esDoctor) {
                // Doctor
                doctorDao.getById(id)?.let { "${it.nombre} ${it.apellido}" }
            } else {
                // Paciente
                pacienteDao.obtenerPacientePorId(id)?.let { "${it.nombre} ${it.apellido}" }
            }
        }
    }
}
