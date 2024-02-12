package com.moralex.testtaskapp.di


import com.google.gson.*
import com.moralex.testtaskapp.data.api.NumbersApiService
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
    fun provideBaseRetrofitBuilder(httpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideBaseRetrofit(url: String, builder: Retrofit.Builder): Retrofit {
        return builder
            .baseUrl(url)
            .build()
    }


    @Singleton
    @Provides
    fun provideProfileApiService(retrofit: Retrofit): NumbersApiService =
        retrofit.create( NumbersApiService::class.java)


}
