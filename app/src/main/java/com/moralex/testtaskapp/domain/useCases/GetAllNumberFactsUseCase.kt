package com.moralex.testtaskapp.domain.useCases

import com.moralex.testtaskapp.data.local.NumberFactDao
import com.moralex.testtaskapp.data.model.NumberFactEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNumberFactsUseCase @Inject constructor(private val numberFactDao: NumberFactDao) {
    fun execute(): Flow<List<NumberFactEntity>> {
        return numberFactDao.getAllNumberFacts()
    }
}