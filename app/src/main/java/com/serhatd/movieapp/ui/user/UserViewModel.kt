package com.serhatd.movieapp.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhatd.movieapp.data.repository.MovieRepository
import com.serhatd.movieapp.data.repository.UserRepository
import com.serhatd.movieapp.ui.callback.ApiCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val apiCallback: ApiCallback, private val mRepo: MovieRepository, private val uRepo: UserRepository): ViewModel() {

    fun changePassword(new_password: String) {
        val user = uRepo.getCurrentSession()
        user?.let {
            user.user_password = new_password
            viewModelScope.launch {
                val response = uRepo.changePassword(user)
                if (response.isSuccessful && response.body()!!.data != null) {
                    apiCallback.onSuccess(response.code(), response.body()?.message ?: response.message())
                    uRepo.setCurrentSession(response.body()!!.data!!)
                } else {
                    apiCallback.onError(response.code(), response.body()?.message ?: response.message())
                }
            }
        }
    }

    fun removeCurrentSession() {
        uRepo.removeCurrentSession()
    }
}