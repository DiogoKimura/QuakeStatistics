package com.example.quakestatistics.data

import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit

object HTTPClient {

    private const val BASE_URL = "https://raw.githubusercontent.com"
    private const val CACHE_TIME = 1
    private val CACHE_TIME_TYPE = TimeUnit.DAYS

    val cacheSize = 10 * 1024 * 1024 // 10 MB
    val httpCacheDirectory = File("/data/data/com.example.quakestatistics/files/", "http-cache")
    val cache = Cache(httpCacheDirectory, cacheSize.toLong())

    val networkCacheInterceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())

        val cacheControl = CacheControl.Builder()
            .maxAge(CACHE_TIME, CACHE_TIME_TYPE)
            .build()

        response.newBuilder()
            .header("Cache-control", cacheControl.toString())
            .build()
    }


    private fun httpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(networkCacheInterceptor)
            .addInterceptor(logging)
            .build()
    }

    fun retrofit() =  Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient())
        .build()



}

