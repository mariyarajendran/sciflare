package com.task.data.remote.service

import com.task.CRUD_ID
import com.task.data.dto.usersdetails.UserDetailsData
import retrofit2.Response
import retrofit2.http.GET

interface UsersService {
    @GET("api/$CRUD_ID/users")
    suspend fun fetchUserDetails(): Response<MutableList<UserDetailsData>>
}
