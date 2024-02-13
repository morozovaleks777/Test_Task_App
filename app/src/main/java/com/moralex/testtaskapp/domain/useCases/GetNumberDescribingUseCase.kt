package com.moralex.testtaskapp.domain.useCases

import com.moralex.testtaskapp.domain.useCases.repository.NumbersRepository
import javax.inject.Inject

class GetNumberDescribingUseCase @Inject constructor (private val numbersRepository: NumbersRepository) {
    suspend fun getDescribing(number: String?) =
        numbersRepository.getMyNumberDescribing(number)


}