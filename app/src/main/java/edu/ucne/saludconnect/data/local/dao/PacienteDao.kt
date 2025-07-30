package edu.ucne.saludconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import edu.ucne.saludconnect.data.local.entity.PacienteEntity

@Dao
interface PacienteDao {
    @Insert
    suspend fun insertarPaciente(paciente: PacienteEntity)

    @Query("SELECT * FROM pacientes")
    suspend fun obtenerPacientes(): List<PacienteEntity>

    @Query("SELECT * FROM pacientes WHERE cedula = :cedula LIMIT 1")
    suspend fun getPacientePorCedula(cedula: Long): PacienteEntity?

    @Query("SELECT * FROM pacientes WHERE id = :id")
    suspend fun obtenerPacientePorId(id: Int): PacienteEntity?

    @Update
    suspend fun actualizarPaciente(paciente: PacienteEntity)

}
