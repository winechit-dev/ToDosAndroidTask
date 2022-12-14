package com.wcp.todosandroidtask.di

import com.wcp.data.datasource.remote.RemoteDataSource
import com.wcp.data.datasource.remote.RemoteRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        remoteDataSourceImpl: RemoteRemoteDataSourceImpl
    ): RemoteDataSource
}