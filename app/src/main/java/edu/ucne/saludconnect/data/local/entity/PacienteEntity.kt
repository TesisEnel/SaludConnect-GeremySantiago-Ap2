package edu.ucne.saludconnect.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "pacientes",
    indices = [Index(value = ["cedula"], unique = true)]
)
data class PacienteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val apellido: String,

    @ColumnInfo(name = "cedula")
    val cedula: Long,

    val edad: String,
    val telefono: String,
    val correo: String,
    val direccion: String,
    val alergias: String,
    val medicamentos: String,
    val condiciones: String
)
