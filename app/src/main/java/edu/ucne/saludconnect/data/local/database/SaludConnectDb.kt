package edu.ucne.saludconnect.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.saludconnect.data.local.dao.DoctorDao
import edu.ucne.saludconnect.data.local.dao.PacienteDao
import edu.ucne.saludconnect.data.local.entity.DoctorEntity
import edu.ucne.saludconnect.data.local.entity.PacienteEntity

@Database(
    entities = [
        PacienteEntity::class,
        DoctorEntity::class],
    version = 2,
    exportSchema = false
)

abstract class SaludConnectDatabase : RoomDatabase() {
    abstract fun pacienteDao(): PacienteDao

    abstract fun doctorDao(): DoctorDao
}
