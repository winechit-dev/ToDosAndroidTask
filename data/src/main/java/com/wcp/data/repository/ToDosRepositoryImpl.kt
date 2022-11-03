package com.wcp.data.repository

import android.net.ConnectivityManager
import com.wcp.data.datasource.local.LocalDataSource
import com.wcp.data.datasource.remote.RemoteDataSource
import com.wcp.data.extensions.isNetworkAvailable
import com.wcp.domain.Resource
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.repository.ToDosRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

class ToDosRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val connectivityManager: ConnectivityManager
) : ToDosRepository {

    private val isOnline get() = connectivityManager.isNetworkAvailable()

    override val toDos: Flow<Resource<List<ToDoModel>>> = if (isOnline) remoteToDos() else localToDos()

    private fun remoteToDos(): Flow<Resource<List<ToDoModel>>> {
        return flow {
            try {
                // throw DataException.Api("Something went wrong!")
                emit(Resource.Success(remoteDataSource.getToDos()))
            } catch (e: Exception) {
                emit(Resource.Error(throwable = e, data = lastCachedToDos()))
            }
        }.onEach { saveInCache(it.data.orEmpty()) }.flowOn(Dispatchers.IO)
    }

    private fun localToDos(): Flow<Resource<List<ToDoModel>>> {
        return flow {
            emit(Resource.Success(localDataSource.getToDos()))
        }
    }

    private suspend fun lastCachedToDos(): List<ToDoModel> {
        return localDataSource.getToDos()
    }

    private suspend fun saveInCache(remoteToDos: List<ToDoModel>) {
        val localToDos = localDataSource.getToDos()

        if (localToDos != remoteToDos) {
            localDataSource.clearToDos()
            localDataSource.saveToDos(remoteToDos)
        }
    }
}