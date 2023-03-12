package com.serhatd.movieapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhatd.movieapp.data.entity.User
import com.serhatd.movieapp.data.repository.UserRepository
import com.serhatd.movieapp.ui.callback.ApiCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val apiCallback: ApiCallback, private val uRepo: UserRepository): ViewModel() {
    val navObserver = MutableLiveData<Boolean>()

    fun login(user_name: String, user_password: String) {
        viewModelScope.launch {
            val response = uRepo.login(user_name, user_password)
            if (response.isSuccessful && response.body()!!.data != null) {
                uRepo.setCurrentSession(response.body()!!.data!!)
                navObserver.value = true
            } else {
                apiCallback.onError(response.code(), response.body()?.message ?: response.message())
            }
        }
    }

    fun getCurrentSession(): User? {
        return uRepo.getCurrentSession()
    }
}