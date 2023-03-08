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

    /*
    fun getCurrentSession(): User? {
        val user = User(
            SharedPrefs.getSharedPreference(context, SharedPrefs.COL_USER_ID, "0").toInt(),
            SharedPrefs.getSharedPreference(context, SharedPrefs.COL_USER_NAME, "0"),
            SharedPrefs.getSharedPreference(context, SharedPrefs.COL_USER_PASSWORD, "0")
        )

        return if (user.user_id == 0) null else user
    }

    private fun setCurrentSession(user: User) {
        val data = HashMap<String, String>()
        data[SharedPrefs.COL_USER_ID] = user.user_id.toString()
        data[SharedPrefs.COL_USER_NAME] = user.user_name
        data[SharedPrefs.COL_USER_PASSWORD] = user.user_password

        SharedPrefs.setSharedPreference(context, data)
    }

    fun removeCurrentSession() {
        SharedPrefs.removeSharedPreference(context, arrayOf(SharedPrefs.COL_USER_ID, SharedPrefs.COL_USER_NAME, SharedPrefs.COL_USER_PASSWORD))
    }
    */
}