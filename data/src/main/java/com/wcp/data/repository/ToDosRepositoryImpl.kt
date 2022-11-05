package com.wcp.data.repository

import android.net.ConnectivityManager
import com.wcp.data.datasource.local.LocalDataSource
import com.wcp.data.datasource.remote.RemoteDataSource
import com.wcp.data.extensions.isNetworkAvailable
import com.wcp.domain.Resource
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.repository.ToDosRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ToDosRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val connectivityManager: ConnectivityManager
) : ToDosRepository {

    private val isOnline get() = connectivityManager.isNetworkAvailable()

    override val toDos: Flow<Resource<List<ToDoModel>>> = localDataSource.getTodosStream()

    override suspend fun fetchToDos(forceUpdate: Boolean) {
        if (isOnline) {
            remoteDataSource.getToDos().data?.let { saveInCache(it, forceUpdate) }
        } else {
            saveInCache(localDataSource.getToDos(), forceUpdate)
        }
    }

    private suspend fun saveInCache(remoteToDos: List<ToDoModel>, forceUpdate: Boolean = false) {

        val localToDos = localDataSource.getToDos()
        when {
            forceUpdate -> {
                localDataSource.clearToDos()
                localDataSource.saveToDos(remoteToDos)
            }
            !remoteToDos.deepEquals(localToDos) -> {
                val toDos = remoteToDos.filter { it.id !in localToDos.map { item -> item.id } }
                localDataSource.saveToDos(toDos)
            }
        }
    }

    private fun List<*>.deepEquals(other: List<*>) =
        this.size == other.size && this.mapIndexed { index, element -> element == other[index] }
            .all { it }

}