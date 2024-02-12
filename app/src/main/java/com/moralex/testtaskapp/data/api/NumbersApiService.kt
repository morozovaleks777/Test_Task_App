package com.moralex.testtaskapp.data.api


import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {

    @GET("{number}")
    suspend fun getNumberFact(@Path("number") number: String): String

    @GET("random/math")
    suspend fun getRandomNumberFact(): String
}