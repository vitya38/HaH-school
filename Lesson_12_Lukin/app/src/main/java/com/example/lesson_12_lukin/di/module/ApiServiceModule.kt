package com.example.lesson_12_lukin.di.module

import com.example.lesson_12_lukin.data.remote.BridgeApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiServiceModule {

    companion object {
        private const val BASE_URL = "http://gdemost.handh.ru:1235/"
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideBridgesApiService(retrofit: Retrofit): BridgeApiService {
        return retrofit.create(BridgeApiService::class.java)
    }
}