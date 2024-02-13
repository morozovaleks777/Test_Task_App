package com.moralex.testtaskapp.domain.useCases

import com.moralex.testtaskapp.domain.useCases.repository.NumbersRepository
import javax.inject.Inject

class GetRandomNumberDescribingUseCase @Inject constructor
    (private val numbersRepository: NumbersRepository) {
    suspend fun getDescribingForRandomNumber() =
        numbersRepository.getRandomNumberDescribing()
}