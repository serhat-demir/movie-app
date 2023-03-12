package com.serhatd.movieapp.data.repository

import com.serhatd.movieapp.data.entity.ApiResponseWithData
import com.serhatd.movieapp.data.entity.User
import com.serhatd.movieapp.data.entity.UserRequest
import com.serhatd.movieapp.data.remote.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository(private val service: UserService) {
    suspend fun login(user_name: String, user_password: String): Response<ApiResponseWithData<User>> {
        val userReq = UserRequest(user_name, user_password)
        return withContext(Dispatchers.IO) {
            service.login(userReq)
        }
    }

    suspend fun changePassword(user: User): Response<ApiResponseWithData<User>> {
        val userReq = UserRequest(user.user_name, user.user_password)
        return withContext(Dispatchers.IO) {
            service.changePassword(user.user_id, userReq)
        }
    }
}