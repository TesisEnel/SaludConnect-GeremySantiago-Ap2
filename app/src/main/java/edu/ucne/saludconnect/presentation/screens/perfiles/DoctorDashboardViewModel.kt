package edu.ucne.saludconnect.presentation.screens.perfiles

import edu.ucne.saludconnect.data.local.dao.DoctorDao
import edu.ucne.saludconnect.data.local.entity.DoctorEntity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DoctorDashboardViewModel @Inject constructor(
    private val doctorDao: DoctorDao
) : ViewModel() {

    private val _doctor = mutableStateOf<DoctorEntity?>(null)
    val doctor: State<DoctorEntity?> = _doctor

    fun cargarDoctor(id: Int) {
        viewModelScope.launch {
            _doctor.value = doctorDao.getById(id)
        }
    }
}
