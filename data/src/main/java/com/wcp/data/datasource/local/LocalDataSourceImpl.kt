package com.wcp.data.datasource.local

import com.wcp.data.database.dao.ToDoDao
import com.wcp.data.database.entity.ToDoEntity
import com.wcp.data.extensions.convertEither
import com.wcp.data.mapper.ToDoMapper
import com.wcp.domain.exception.DataException
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.type.Either
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val toDoDao: ToDoDao,
    private val mapper: ToDoMapper
) : LocalDataSource {
    override suspend fun getToDoEntities(): Either<DataException, List<ToDoModel>> {
        return try {
            val data = mapper.mapToDoModels(toDoDao.getToDos())
            Either.Right(data)
        } catch (e: Exception) {
            e.convertEither()
        }
    }

    override suspend fun saveToDoEntities(entities: List<ToDoEntity>) {
        toDoDao.insertToDos(entities)
    }
}