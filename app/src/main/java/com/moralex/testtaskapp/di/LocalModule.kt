package com.moralex.testtaskapp.di

import android.content.Context
import androidx.room.Room
import com.moralex.testtaskapp.data.local.NumberFactDao
import com.moralex.testtaskapp.data.local.NumberFactsDatabase
import com.moralex.testtaskapp.domain.useCases.repository.NumberFactsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): NumberFactsDatabase {
        return Room.databaseBuilder(
            appContext,
            NumberFactsDatabase::class.java,
            "number_facts_database"
        ).build()
    }

    @Provides
    fun provideNumberFactDao(database: NumberFactsDatabase): NumberFactDao {
        return database.numberFactDao()
    }

    @Provides
    @Singleton
    fun provideNumberFactsRepository(numberFactDao: NumberFactDao): NumberFactsRepository {
        return NumberFactsRepository(numberFactDao)
    }
}