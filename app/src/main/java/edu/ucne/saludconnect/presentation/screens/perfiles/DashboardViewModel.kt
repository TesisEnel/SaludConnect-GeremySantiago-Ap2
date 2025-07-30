package edu.ucne.saludconnect.presentation.screens.perfiles

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.saludconnect.data.local.dao.PacienteDao
import edu.ucne.saludconnect.data.local.entity.PacienteEntity
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val pacienteDao: PacienteDao
) : ViewModel() {

    private val _paciente = mutableStateOf<PacienteEntity?>(null)
    val paciente: State<PacienteEntity?> = _paciente

    fun cargarPaciente(id: Int) {
        viewModelScope.launch {
            _paciente.value = pacienteDao.obtenerPacientePorId(id)
        }
    }
}
