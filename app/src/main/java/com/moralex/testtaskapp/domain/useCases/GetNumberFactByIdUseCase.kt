package com.moralex.testtaskapp.domain.useCases

import com.moralex.testtaskapp.data.local.NumberFactDao
import com.moralex.testtaskapp.data.model.NumberFactEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetNumberFactByIdUseCase  @Inject constructor (private val numberFactDao: NumberFactDao) {
    fun execute(id: Long): Flow<NumberFactEntity?> {
        return numberFactDao.getNumberFactById(id)
    }

}

class GetNumberFactByTextUseCase  @Inject constructor (private val numberFactDao: NumberFactDao) {
    fun execute(text: String): Flow<NumberFactEntity?> {
        return numberFactDao.getNumberFactByText(text)
    }

}