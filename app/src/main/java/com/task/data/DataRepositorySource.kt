package com.task.data

import kotlinx.coroutines.flow.Flow


interface DataRepositorySource {
    suspend fun fetchUserDetails(): Flow<Resource<Any>>
}
