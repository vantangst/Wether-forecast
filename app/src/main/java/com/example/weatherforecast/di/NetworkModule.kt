package com.example.weatherforecast.di

import android.content.Context
import com.example.weatherforecast.data.api.ApiClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class OtherInterceptorOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class CacheInterceptorOkHttpClient

    @CacheInterceptorOkHttpClient
    @Provides
    fun provideCacheInterceptorOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return ApiClientFactory.createWithCache(context)
    }
}