package com.serhatd.movieapp.data.remote

import com.serhatd.movieapp.data.entity.ApiResponseWithData
import com.serhatd.movieapp.data.entity.User
import com.serhatd.movieapp.data.entity.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @POST("users")
    suspend fun login(
        @Body user: UserRequest
    ): Response<ApiResponseWithData<User>>

    @PUT("users/{user_id}")
    suspend fun changePassword(
        @Path("user_id") user_id: Int,
        @Body user: UserRequest
    ): Response<ApiResponseWithData<User>>
}