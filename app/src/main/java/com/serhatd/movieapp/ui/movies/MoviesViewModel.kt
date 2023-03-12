package com.serhatd.movieapp.ui.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhatd.movieapp.data.entity.Movie
import com.serhatd.movieapp.data.repository.MovieRepository
import com.serhatd.movieapp.ui.callback.ApiCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val apiCallback: ApiCallback, private val mRepo: MovieRepository): ViewModel() {
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
}