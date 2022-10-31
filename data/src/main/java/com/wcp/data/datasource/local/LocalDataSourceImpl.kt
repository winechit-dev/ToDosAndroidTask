package com.wcp.data.datasource.local

import com.wcp.data.database.dao.ToDoDao
import com.wcp.data.mapper.ToDoMapper
import com.wcp.domain.model.ToDoModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao,
    private val mapper: ToDoMapper
) : LocalDataSource {
    override suspend fun getToDos(): List<ToDoModel> {
        return mapper.mapToDoModels(toDoDao.getToDos())
    }

    override suspend fun saveToDos(entities: List<ToDoModel>) {
        toDoDao.insertToDos(mapper.mapToDoEntities(entities))
    }

    override suspend fun clearToDos() {
        toDoDao.clearToDos()
    }
}