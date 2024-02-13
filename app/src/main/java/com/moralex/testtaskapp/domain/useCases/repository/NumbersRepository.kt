package com.moralex.testtaskapp.domain.useCases.repository

import com.moralex.testtaskapp.data.model.NumberFact
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface NumbersRepository {
     suspend fun getMyNumberDescribing(number: String?): Flow<Result<ResponseBody>>
     suspend fun getRandomNumberDescribing(): Flow<Result<ResponseBody>>
}

