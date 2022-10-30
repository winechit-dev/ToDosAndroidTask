package com.wcp.data.repository

import android.net.ConnectivityManager
import com.wcp.data.datasource.local.LocalDataSource
import com.wcp.data.datasource.remote.RemoteDataSource
import com.wcp.data.mapper.ToDoMapper
import com.wcp.domain.exception.DataException
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.repository.ToDosRepository
import com.wcp.domain.type.Either
import javax.inject.Inject

class ToDosRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val mapper: ToDoMapper,
    private val connectivityManager: ConnectivityManager
) : ToDosRepository {

    @Suppress("DEPRECATION")
    override val isOnline: Boolean
        get() = connectivityManager.activeNetworkInfo?.isConnected == true

    override suspend fun getToDos(): Either<DataException, List<ToDoModel>> {
        return if (isOnline) {
            remoteDataSource.getToDos().let {
                it.rightOrNull()?.value?.let { toDos ->
                    val entities = mapper.mapToDoEntities(toDos)
                    localDataSource.saveToDoEntities(entities)
                }
                it
            }
        } else {
            localDataSource.getToDoEntities()
        }
    }
}