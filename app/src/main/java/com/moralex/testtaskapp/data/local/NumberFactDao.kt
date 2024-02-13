package com.moralex.testtaskapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moralex.testtaskapp.data.model.NumberFactEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberFactDao {
    @Query("SELECT * FROM number_facts")
    fun getAllNumberFacts(): Flow<List<NumberFactEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNumberFact(numberFact: NumberFactEntity)

    @Query("SELECT * FROM number_facts WHERE id = :id")
    fun getNumberFactById(id: Long): Flow<NumberFactEntity?>

    @Query("SELECT * FROM number_facts WHERE text = :text")
    fun getNumberFactByText(text: String): Flow<NumberFactEntity?>
}