package edu.ucne.saludconnect.di

import android.content.Context
import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.saludconnect.data.local.dao.PacienteDao
import edu.ucne.saludconnect.data.local.database.SaludConnectDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): SaludConnectDatabase {
        return Room.databaseBuilder(
            app,
            SaludConnectDatabase::class.java,
            "saludconnect_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providePacienteDao(db: SaludConnectDatabase): PacienteDao {
        return db.pacienteDao()
    }
}
