package com.serhatd.movieapp.di

import com.serhatd.movieapp.data.remote.MovieService
import com.serhatd.movieapp.data.remote.UserService
import com.serhatd.movieapp.data.repository.MovieRepository
import com.serhatd.movieapp.data.repository.UserRepository
import com.serhatd.movieapp.ui.callback.ApiCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideUserRepository(userService: UserService): UserRepository {
        return UserRepository(userService)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(movieService: MovieService): MovieRepository {
        return MovieRepository(movieService)
    }

    @Singleton
    @Provides
    fun provideApiCallback(): ApiCallback {
        return object: ApiCallback {
            override fun onSuccess(code: Int, message: String) {}
            override fun onError(code: Int, message: String) {}
        }
    }
}