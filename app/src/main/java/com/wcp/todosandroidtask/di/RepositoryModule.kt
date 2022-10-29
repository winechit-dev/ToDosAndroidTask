package com.wcp.todosandroidtask.di

import com.wcp.data.repository.ToDosRepositoryImpl
import com.wcp.domain.repository.ToDosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAccountRepository(
        toDosRepositoryImpl: ToDosRepositoryImpl
    ): ToDosRepository
}