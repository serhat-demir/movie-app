package com.serhatd.movieapp.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhatd.movieapp.data.entity.Movie
import com.serhatd.movieapp.data.repository.MovieRepository
import com.serhatd.movieapp.data.repository.UserRepository
import com.serhatd.movieapp.ui.callback.ApiCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val apiCallback: ApiCallback, private val mRepo: MovieRepository, private val uRepo: UserRepository): ViewModel() {
    val movies = MutableLiveData<List<Movie>>()

    fun getMovies() {
        viewModelScope.launch {
            val response = mRepo.getMovies()
            if (response.isSuccessful) {
                movies.value = response.body()!!.data!!
            } else {
                apiCallback.onError(response.code(), response.body()?.message ?: response.message())
            }
        }
    }

    fun addMovie(movie_name: String, movie_image: String, movie_url: String) {
        viewModelScope.launch {
            val response = mRepo.addMovie(movie_name, movie_image, movie_url)
            if (response.isSuccessful) {
                getMovies()

            } else {
                apiCallback.onError(response.code(), response.body()?.message ?: response.message())
            }
        }
    }

    fun editMovie(movie_id: Int, movie_name: String, movie_image: String, movie_url: String) {
        viewModelScope.launch {
            val response = mRepo.editMovie(movie_id, movie_name, movie_image, movie_url)
            if (response.isSuccessful) {
                getMovies()
            } else {
                apiCallback.onError(response.code(), response.body()?.message ?: response.message())
            }
        }
    }

    fun deleteMovie(movie_id: Int) {
        viewModelScope.launch {
            val response = mRepo.deleteMovie(movie_id)
            if (response.isSuccessful) {
                getMovies()
            } else {
                apiCallback.onError(response.code(), response.body()?.message ?: response.message())
            }
        }
    }

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