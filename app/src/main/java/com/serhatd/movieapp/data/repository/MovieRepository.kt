package com.serhatd.movieapp.data.repository

import android.content.Context
import com.serhatd.movieapp.data.entity.MovieRequest
import com.serhatd.movieapp.data.remote.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository(private val context: Context, private val service: MovieService) {
    suspend fun getMovies() {
        withContext(Dispatchers.IO) {
            service.getMovies()
        }
    }

    suspend fun addMovie(movie_name: String, movie_image: String, movie_url: String) {
        val movieReq = MovieRequest(movie_name, movie_image, movie_url)
        withContext(Dispatchers.IO) {
            service.addMovie(movieReq)
        }
    }

    suspend fun editMovie(movie_id: Int, movie_name: String, movie_image: String, movie_url: String) {
        val movieReq = MovieRequest(movie_name, movie_image, movie_url)
        withContext(Dispatchers.IO) {
            service.editMovie(movie_id, movieReq)
        }
    }

    suspend fun deleteMovie(movie_id: Int) {
        withContext(Dispatchers.IO) {
            service.deleteMovie(movie_id)
        }
    }
}