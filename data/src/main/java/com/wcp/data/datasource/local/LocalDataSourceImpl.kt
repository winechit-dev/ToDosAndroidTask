package com.wcp.data.datasource.local

import com.wcp.data.database.dao.ToDoDao
import com.wcp.data.database.entity.ToDoEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao
) : LocalDataSource {
    override suspend fun getToDoEntities(): List<ToDoEntity> {
        return toDoDao.getToDos()
    }

    override suspend fun saveToDoEntities(entities: List<ToDoEntity>) {
        toDoDao.insertToDos(entities)
    }

    override suspend fun clearToDos() {
        toDoDao.clearToDos()
    }
}