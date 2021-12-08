package com.example.weatherforecast.di

import com.example.weatherforecast.data.api.ApiService
import com.example.weatherforecast.data.api.ApiServiceClient
import dagger.Provides
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providerCooker(@NetworkModule.OtherInterceptorOkHttpClient okHttpClient: OkHttpClient): ApiService {
        return ApiServiceClient.build(okHttpClient)
    }
}