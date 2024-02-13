package com.moralex.testtaskapp.data



import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersApiService {

    @GET("{number}")
    suspend fun getNumberFact(@Path("number") number: String?): ResponseBody // Отримуємо тіло відповіді

    @GET("random/math")
    suspend fun getRandomNumberFact(): ResponseBody // Отримуємо тіло відповіді
}

