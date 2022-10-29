package com.wcp.todosandroidtask.di

import com.wcp.data.BuildConfig
import com.wcp.data.network.createOkHttpClient
import com.wcp.data.network.createRetrofitClient
import com.wcp.data.service.ToDoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return createRetrofitClient(BuildConfig.JSON_URL, okHttpClient)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return createOkHttpClient(
            HttpLoggingInterceptor()
        )
    }

    @Provides
    @Singleton
    fun provideMovieService(retrofit: Retrofit): ToDoService {
        return retrofit.create(ToDoService::class.java)
    }
}
