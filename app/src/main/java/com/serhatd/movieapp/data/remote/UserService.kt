package com.serhatd.movieapp.data.remote

import com.serhatd.movieapp.data.entity.ApiResponseWithData
import com.serhatd.movieapp.data.entity.User
import com.serhatd.movieapp.data.entity.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserService {
    @POST("users")
    suspend fun login(
        @Body user: UserRequest
    ): Response<ApiResponseWithData<User>>

    @PUT("users")
    suspend fun changePassword(
        @Body user: UserRequest
    ): Response<ApiResponseWithData<User>>
}