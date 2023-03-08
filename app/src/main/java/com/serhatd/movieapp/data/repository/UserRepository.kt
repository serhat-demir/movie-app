package com.serhatd.movieapp.data.repository

import android.content.Context
import com.serhatd.movieapp.data.entity.User
import com.serhatd.movieapp.data.entity.UserRequest
import com.serhatd.movieapp.data.prefs.SharedPrefs
import com.serhatd.movieapp.data.remote.UserService
import com.serhatd.movieapp.ui.callback.ApiCallback

class UserRepository(private val context: Context, private val callback: ApiCallback, private val service: UserService) {
    suspend fun login(user_name: String, user_password: String) {
        val userReq = UserRequest(user_name, user_password)
        val response = service.login(userReq)

        if (response.isSuccessful && response.body() != null) {
            setCurrentSession(response.body()!!.data)
            callback.onSuccess(response.code(), response.body()?.message ?: response.message())
        } else {
            callback.onError(response.code(), response.body()?.message ?: response.message())
        }
    }

    suspend fun changePassword(new_password: String) {
        val user = getCurrentSession()
        user?.let {
            user.user_password = new_password

            val userReq = UserRequest(user.user_name, user.user_password)
            val response = service.changePassword(user.user_id, userReq)

            if (response.isSuccessful && response.body() != null) {
                setCurrentSession(user)
                callback.onSuccess(response.code(), response.body()!!.message)
            } else {
                callback.onError(response.code(), response.body()?.message ?: response.message())
            }
        }
    }

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
}