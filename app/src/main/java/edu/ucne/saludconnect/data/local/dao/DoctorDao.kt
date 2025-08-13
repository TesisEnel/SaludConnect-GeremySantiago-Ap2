package edu.ucne.saludconnect.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.saludconnect.data.local.entity.DoctorEntity

@Dao
interface DoctorDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(doctor: DoctorEntity)

    @Query("SELECT * FROM doctores WHERE cedula = :cedula LIMIT 1")
    suspend fun getByCedula(cedula: String): DoctorEntity?

    @Query("SELECT * FROM doctores ORDER BY nombre")
    suspend fun getAll(): List<DoctorEntity>

    @Query("SELECT * FROM doctores WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): DoctorEntity?

}
