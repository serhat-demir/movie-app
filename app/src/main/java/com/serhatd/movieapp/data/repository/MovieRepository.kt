package com.serhatd.movieapp.data.repository

import com.serhatd.movieapp.data.entity.ApiResponse
import com.serhatd.movieapp.data.entity.ApiResponseWithData
import com.serhatd.movieapp.data.entity.Movie
import com.serhatd.movieapp.data.entity.MovieRequest
import com.serhatd.movieapp.data.remote.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieRepository(private val service: MovieService) {
    suspend fun getMovies(): Response<ApiResponseWithData<List<Movie>>> {
        return withContext(Dispatchers.IO) {
            service.getMovies()
        }
    }

    suspend fun addMovie(movie_name: String, movie_image: String, movie_url: String): Response<ApiResponse> {
        val movieReq = MovieRequest(movie_name, movie_image, movie_url)
        return withContext(Dispatchers.IO) {
            service.addMovie(movieReq)
        }
    }

    suspend fun editMovie(movie_id: Int, movie_name: String, movie_image: String, movie_url: String): Response<ApiResponse> {
        val movieReq = MovieRequest(movie_name, movie_image, movie_url)
        return withContext(Dispatchers.IO) {
            service.editMovie(movie_id, movieReq)
        }
    }

    suspend fun deleteMovie(movie_id: Int): Response<ApiResponse> {
        return withContext(Dispatchers.IO) {
            service.deleteMovie(movie_id)
        }
    }
}