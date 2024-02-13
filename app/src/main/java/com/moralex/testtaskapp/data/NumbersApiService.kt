package com.moralex.testtaskapp.data


import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {

    @GET("{number}")
    suspend fun getNumberFact(@Path("number") number: String?): ResponseBody

    @GET("random/math")
    suspend fun getRandomNumberFact(): ResponseBody
}

