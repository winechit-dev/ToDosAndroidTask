package com.wcp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wcp.data.database.entity.ToDoEntity

@Dao
interface ToDoDao {
    @Query("SELECT * FROM toDos")
    suspend fun getToDos(): List<ToDoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertToDos(toDoEntities: List<ToDoEntity>)

    @Query("DELETE FROM toDos")
    suspend fun clearToDos()
}