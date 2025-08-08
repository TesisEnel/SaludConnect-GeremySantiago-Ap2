package edu.ucne.saludconnect.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "doctores",
    indices = [Index(value = ["licencia"], unique = true)]
)
data class DoctorEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val licencia: String,          // cédula profesional
    val especialidad: String,
    val telefono: String,
    val correo: String,
    val hospital: String,          // u “centro de salud”
    val bio: String = ""           // opcional
)
