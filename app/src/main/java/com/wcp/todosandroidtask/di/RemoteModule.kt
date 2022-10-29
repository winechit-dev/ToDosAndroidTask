package com.wcp.todosandroidtask.di

import com.wcp.data.datasource.RemoteDataSource
import com.wcp.data.datasource.RemoteDataSourceImpl
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
    abstract fun bindRemote(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource
}