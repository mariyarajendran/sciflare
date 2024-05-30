package com.task.data

import com.task.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val ioDispatcher: CoroutineContext
) : DataRepositorySource {
    override suspend fun fetchUserDetails(): Flow<Resource<Any>> {
        return flow {
            emit(remoteRepository.fetchUserDetails())
        }.flowOn(ioDispatcher)
    }
}
