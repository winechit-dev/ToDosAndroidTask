package com.wcp.data.datasource.local

import com.wcp.data.database.dao.ToDoDao
import com.wcp.data.mapper.ToDoMapper
import com.wcp.domain.Resource
import com.wcp.domain.model.ToDoModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao,
    private val mapper: ToDoMapper
) : LocalDataSource {
    override fun getTodosStream(): Flow<Resource<List<ToDoModel>>> {
        return toDoDao.observeTask().map {
            Resource.Success(mapper.mapToDoModels(it))
        }
    }

    override suspend fun getToDos(): List<ToDoModel> {
        return withContext(Dispatchers.IO) {
            return@withContext mapper.mapToDoModels(toDoDao.getToDos())
        }
    }

    override suspend fun saveToDos(entities: List<ToDoModel>) {
        withContext(Dispatchers.IO) {
            toDoDao.insertToDos(mapper.mapToDoEntities(entities))
        }
    }

    override suspend fun clearToDos() {
        withContext(Dispatchers.IO) {
            toDoDao.clearToDos()
        }
    }
}