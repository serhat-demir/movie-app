package com.serhatd.movieapp.di

import android.content.Context
import com.serhatd.movieapp.data.prefs.SharedPrefs
import com.serhatd.movieapp.data.remote.MovieService
import com.serhatd.movieapp.data.remote.UserService
import com.serhatd.movieapp.data.repository.MovieRepository
import com.serhatd.movieapp.data.repository.UserRepository
import com.serhatd.movieapp.ui.callback.ApiCallback
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideApiCallback(@ApplicationContext context: Context): ApiCallback {
        return ApiCallback(context)
    }

    @Singleton
    @Provides
    fun providePrefs(@ApplicationContext context: Context): SharedPrefs {
        return SharedPrefs(context)
    }
}