package com.wcp.todosandroidtask.di

import com.wcp.domain.repository.ToDosRepository
import com.wcp.domain.usecase.GetToDos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    @Singleton
    fun provideGetToDosUseCase(
        toDosRepository: ToDosRepository
    ): GetToDos {
        return GetToDos(toDosRepository)
    }
}