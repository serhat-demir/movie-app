package com.serhatd.movieapp.data.remote

import com.serhatd.movieapp.data.entity.ApiResponse
import com.serhatd.movieapp.data.entity.ApiResponseWithData
import com.serhatd.movieapp.data.entity.Movie
import com.serhatd.movieapp.data.entity.MovieRequest
import retrofit2.Response
import retrofit2.http.*

interface MovieService {
    @GET("movies")
    suspend fun getMovies(): Response<ApiResponseWithData<Movie>>

    @POST("movies")
    suspend fun addMovie(
        @Body movie: MovieRequest
    ): Response<ApiResponse>

    @PUT("movies/{movie_id}")
    suspend fun editMovie(
        @Path("movie_id") movie_id: Int,
        @Body movie: MovieRequest
    ): Response<ApiResponse>

    @DELETE("movies/{movie_id}")
    suspend fun deleteMovie(
        @Path("movie_id") movie_id: Int
    ): Response<ApiResponse>
}