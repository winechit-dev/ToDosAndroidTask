package com.wcp.data.network

import android.content.Context
import com.wcp.data.BuildConfig
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun createRetrofitClient(url: String, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(url)
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

fun createOkHttpClient(
    context: Context,
    httpLoggingInterceptor: HttpLoggingInterceptor
) = OkHttpClient.Builder()
    .also {
        if (BuildConfig.DEBUG) {
            it.addInterceptor(
                httpLoggingInterceptor.apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }
    }
    .readTimeout(60L, TimeUnit.SECONDS)
    .writeTimeout(60L, TimeUnit.SECONDS)
    .build()
