package com.task.data.remote

import com.task.data.Resource

internal interface RemoteDataSource {
    suspend fun fetchUserDetails(): Resource<Any>
}
