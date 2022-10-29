package com.wcp.data.datasource

import com.wcp.domain.exception.DataException
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.type.Either

interface RemoteDataSource {
    suspend fun getToDos(): Either<DataException, List<ToDoModel>>
}