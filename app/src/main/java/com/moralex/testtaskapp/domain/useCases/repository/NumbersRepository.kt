package com.moralex.testtaskapp.domain.useCases.repository


import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface NumbersRepository {
     suspend fun getMyNumberDescribing(number: String?): Flow<Result<ResponseBody>>
     suspend fun getRandomNumberDescribing(): Flow<Result<ResponseBody>>
}

