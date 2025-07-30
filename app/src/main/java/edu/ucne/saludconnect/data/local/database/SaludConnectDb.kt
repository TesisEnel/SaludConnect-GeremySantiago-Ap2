package edu.ucne.saludconnect.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.saludconnect.data.local.dao.PacienteDao
import edu.ucne.saludconnect.data.local.entity.PacienteEntity

@Database(
    entities = [PacienteEntity::class],
    version = 1)

abstract class SaludConnectDatabase : RoomDatabase() {
    abstract fun pacienteDao(): PacienteDao
}
