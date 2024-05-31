package com.task.data.remote.service

import com.task.BuildConfig
import com.task.data.dto.usersdetails.UserDetailsData
import retrofit2.Response
import retrofit2.http.GET

interface UsersService {
    @GET("api/${BuildConfig.CRUD_ID}/users")
    suspend fun fetchUserDetails(): Response<MutableList<UserDetailsData>>
}
