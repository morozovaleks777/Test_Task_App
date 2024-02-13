package com.moralex.testtaskapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moralex.testtaskapp.data.model.NumberFactEntity

@Database(entities = [NumberFactEntity::class], version = 2)
abstract class NumberFactsDatabase : RoomDatabase() {
    abstract fun numberFactDao(): NumberFactDao
}