package com.wcp.todosandroidtask.di

import com.wcp.data.datasource.local.LocalDataSource
import com.wcp.data.datasource.local.LocalDataSourceImpl
import com.wcp.data.datasource.remote.RemoteDataSource
import com.wcp.data.datasource.remote.RemoteRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {
    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource
}