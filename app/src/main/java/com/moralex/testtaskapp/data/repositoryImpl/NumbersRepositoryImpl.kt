package com.moralex.testtaskapp.data.repositoryImpl


import com.moralex.testtaskapp.data.NumbersApiService
import com.moralex.testtaskapp.domain.useCases.repository.NumbersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import javax.inject.Inject

class NumbersRepositoryImpl @Inject constructor(
    private val numbersApiService: NumbersApiService
) : NumbersRepository {

    override suspend fun getMyNumberDescribing(number: String?): Flow<Result<ResponseBody>> = flow {
        try {
            val result = numbersApiService.getNumberFact(number)
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }


    override suspend fun getRandomNumberDescribing(): Flow<Result<ResponseBody>> = flow {
        try {
            val result = numbersApiService.getRandomNumberFact()
            emit(Result.success(result))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}

