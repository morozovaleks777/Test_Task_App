package com.moralex.testtaskapp.domain.useCases

import com.moralex.testtaskapp.data.local.NumberFactDao
import com.moralex.testtaskapp.data.model.NumberFactEntity
import javax.inject.Inject

class SaveNumberFactUseCase @Inject constructor(private val numberFactDao: NumberFactDao) {
    suspend fun execute(numberFact: NumberFactEntity) {
        numberFactDao.insertNumberFact(numberFact)
    }
}