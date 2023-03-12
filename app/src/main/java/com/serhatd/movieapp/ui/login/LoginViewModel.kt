package com.serhatd.movieapp.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhatd.movieapp.data.entity.User
import com.serhatd.movieapp.data.prefs.SharedPrefs
import com.serhatd.movieapp.data.repository.UserRepository
import com.serhatd.movieapp.ui.callback.ApiCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val prefs: SharedPrefs, private val apiCallback: ApiCallback, private val uRepo: UserRepository): ViewModel() {
    val navObserver = MutableLiveData<Boolean>()

    fun login(user_name: String, user_password: String) {
        viewModelScope.launch {
            val response = uRepo.login(user_name, user_password)
            if (response.isSuccessful) {
                setCurrentSession(response.body()!!.data)
                navObserver.value = true
            } else {
                apiCallback.onError(response.code(), response.body()?.message ?: response.message())
            }
        }
    }

    private fun setCurrentSession(user: User) {
        val data = HashMap<String, String>()
        data[SharedPrefs.COL_USER_ID] = user.user_id.toString()
        data[SharedPrefs.COL_USER_NAME] = user.user_name
        data[SharedPrefs.COL_USER_PASSWORD] = user.user_password

        prefs.setSharedPreference(data)
    }
}