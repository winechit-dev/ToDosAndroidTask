package com.wcp.data.datasource.local

import com.wcp.data.database.entity.ToDoEntity
import com.wcp.domain.exception.DataException
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.type.Either

interface LocalDataSource {
    suspend fun getToDoEntities(): Either<DataException, List<ToDoModel>>
    suspend fun saveToDoEntities(entities: List<ToDoEntity>)
}