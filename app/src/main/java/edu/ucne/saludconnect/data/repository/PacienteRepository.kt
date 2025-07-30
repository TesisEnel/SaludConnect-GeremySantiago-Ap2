package edu.ucne.saludconnect.data.repository

import edu.ucne.saludconnect.data.local.entity.PacienteEntity
import edu.ucne.saludconnect.data.local.dao.PacienteDao
import javax.inject.Inject

class PacienteRepository @Inject constructor(
    private val pacienteDao: PacienteDao
) {
    suspend fun insertarPaciente(paciente: PacienteEntity) {
        pacienteDao.insertarPaciente(paciente)
    }

    suspend fun getPacientePorCedula(cedula: Long): PacienteEntity? {
        return pacienteDao.getPacientePorCedula(cedula)
    }
}
