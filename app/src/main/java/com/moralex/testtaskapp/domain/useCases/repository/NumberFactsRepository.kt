package com.moralex.testtaskapp.domain.useCases.repository

import com.moralex.testtaskapp.data.local.NumberFactDao
import com.moralex.testtaskapp.data.model.NumberFactEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NumberFactsRepository @Inject constructor(
    private val numberFactDao: NumberFactDao
) {
    fun getAllNumberFacts(): Flow<List<NumberFactEntity>> {
        return numberFactDao.getAllNumberFacts()
    }

    suspend fun insertNumberFact(numberFact: NumberFactEntity) {
        numberFactDao.insertNumberFact(numberFact)
    }
}