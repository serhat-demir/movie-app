package com.serhatd.movieapp.di

import com.serhatd.movieapp.data.remote.MovieService
import com.serhatd.movieapp.data.remote.UserService
import com.serhatd.movieapp.data.repository.MovieRepository
import com.serhatd.movieapp.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideUserRepository(userService: UserService): UserRepository {
        return UserRepository(userService)
    }

    @Provides
    fun provideMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepository(movieService)
    }
}