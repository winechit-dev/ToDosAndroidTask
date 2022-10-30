package com.wcp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wcp.data.database.dao.ToDoDao
import com.wcp.data.database.entity.ToDoEntity

@Database(entities = [ToDoEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}