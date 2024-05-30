package com.task.data.remote

import com.task.data.Resource
import com.task.data.error.NETWORK_ERROR
import com.task.data.error.NO_INTERNET_CONNECTION
import com.task.data.remote.service.UsersService
import com.task.data.room.dao.RoomDatabase
import com.task.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


class RemoteData @Inject
constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetworkConnectivity,
    private val db: RoomDatabase
) : RemoteDataSource {
    private val dao = db.roomDao()
    override suspend fun fetchUserDetails(): Resource<Any> {
        val usersService = serviceGenerator.createService(UsersService::class.java)
        return when (val response = processCall { usersService.fetchUserDetails() }) {
            is Any -> {
                Resource.Success(data = response)
            }

            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }


    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }


}
