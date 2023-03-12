package com.serhatd.movieapp.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhatd.movieapp.data.entity.User
import com.serhatd.movieapp.data.prefs.SharedPrefs
import com.serhatd.movieapp.data.repository.MovieRepository
import com.serhatd.movieapp.data.repository.UserRepository
import com.serhatd.movieapp.ui.callback.ApiCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val prefs: SharedPrefs, private val apiCallback: ApiCallback, private val mRepo: MovieRepository, private val uRepo: UserRepository): ViewModel() {

    fun changePassword(new_password: String) {
        val user = getCurrentSession()
        user?.let {
            user.user_password = new_password
            viewModelScope.launch {
                val response = uRepo.changePassword(user)
                if (response.isSuccessful && response.body()!!.data != null) {
                    apiCallback.onSuccess(response.code(), response.body()?.message ?: response.message())
                    setCurrentSession(response.body()!!.data!!)
                } else {
                    apiCallback.onError(response.code(), response.body()?.message ?: response.message())
                }
            }
        }
    }

    private fun getCurrentSession(): User? {
        val user = User(
            prefs.getSharedPreference(SharedPrefs.COL_USER_ID, "0").toInt(),
            prefs.getSharedPreference(SharedPrefs.COL_USER_NAME, "0"),
            prefs.getSharedPreference(SharedPrefs.COL_USER_PASSWORD, "0")
        )

        return if (user.user_id == 0) null else user
    }

    private fun setCurrentSession(user: User) {
        val data = HashMap<String, String>()
        data[SharedPrefs.COL_USER_ID] = user.user_id.toString()
        data[SharedPrefs.COL_USER_NAME] = user.user_name
        data[SharedPrefs.COL_USER_PASSWORD] = user.user_password

        prefs.setSharedPreference(data)
    }

    fun removeCurrentSession() {
        prefs.removeSharedPreference(arrayOf(SharedPrefs.COL_USER_ID, SharedPrefs.COL_USER_NAME, SharedPrefs.COL_USER_PASSWORD))
    }
}