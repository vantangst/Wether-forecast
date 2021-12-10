package com.example.weatherforecast.data.api

import android.content.Context
import com.example.weatherforecast.BuildConfig
import com.example.weatherforecast.extension.isNetworkAvailable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val READ_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 30L
private const val CONNECT_TIMEOUT = 30L
private const val CACHE_CONTROL = "Cache-Control"
private const val TIME_CACHE_ONLINE = "public, max-age=" + 60 // 1 minute
private const val TIME_CACHE_OFFLINE = "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 1 // 1 day

object ApiClientFactory {

    private fun okHttpClientBuilder(): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()
        httpClientBuilder.apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.apply {
                    logging.level = HttpLoggingInterceptor.Level.BODY
                }
                interceptors().add(logging)
            }
        }
        return httpClientBuilder
    }

    fun create(): OkHttpClient {
        val httpClient = okHttpClientBuilder()
        return httpClient.build()
    }

    fun createWithCache(context: Context): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)
        val httpClient = okHttpClientBuilder()
        httpClient.apply {
            cache(cache)
            addInterceptor { chain ->
                var request = chain.request()
                request = if (context.isNetworkAvailable(context)) {
                    request.newBuilder().header(
                        CACHE_CONTROL,
                        TIME_CACHE_ONLINE,
                    ).build()
                } else {
                    request.newBuilder().header(
                        CACHE_CONTROL,
                        TIME_CACHE_OFFLINE,
                    ).build()
                }
                chain.proceed(request)
            }
        }
        return httpClient.build()
    }
}