package com.wcp.data.repository

import com.wcp.data.datasource.local.LocalDataSource
import com.wcp.data.datasource.remote.RemoteDataSource
import com.wcp.data.mapper.ToDoMapper
import com.wcp.domain.Resource
import com.wcp.domain.exception.DataException
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.repository.ToDosRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

class ToDosRepositoryImpl @Inject constructor(
    remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mapper: ToDoMapper
) : ToDosRepository {

    override val toDos: Flow<Resource<List<ToDoModel>>> = remoteDataSource.toDos
        .onEach { saveInCache(it.data) }
        .flowOn(Dispatchers.IO)
        .catch { exception -> // Executes in the consumer's context
            when (exception) {
                DataException.Network -> emit(Resource.Success(data = lastCachedToDos()))
                else -> emit(Resource.Error(throwable = exception, data = lastCachedToDos()))
            }
        }

    private suspend fun lastCachedToDos(): List<ToDoModel> {
        return mapper.mapToDoModels(localDataSource.getToDoEntities())
    }

    private suspend fun saveInCache(toDoModels: List<ToDoModel>?) {
        toDoModels?.let {
            localDataSource.clearToDos()
            localDataSource.saveToDoEntities(mapper.mapToDoEntities(it))
        }
    }
}