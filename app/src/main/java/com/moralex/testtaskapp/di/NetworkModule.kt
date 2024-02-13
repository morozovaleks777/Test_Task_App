package com.moralex.testtaskapp.di


import com.moralex.testtaskapp.data.NumbersApiService
import com.moralex.testtaskapp.data.repositoryImpl.NumbersRepositoryImpl
import com.moralex.testtaskapp.domain.useCases.repository.NumbersRepository
import com.moralex.testtaskapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRepository(numbersRepositoryImpl: NumbersRepositoryImpl): NumbersRepository {
        return numbersRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideOpenWeatherApi(): NumbersApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(BASE_URL)
            .build().create(NumbersApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()
}