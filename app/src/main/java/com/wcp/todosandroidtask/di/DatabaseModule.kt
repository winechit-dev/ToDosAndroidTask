package com.wcp.todosandroidtask.di

import android.content.Context
import androidx.room.Room
import com.wcp.data.database.AppDatabase
import com.wcp.data.database.dao.ToDoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java,
            "main-db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideToDoDao(database: AppDatabase): ToDoDao {
        return database.toDoDao()
    }
}