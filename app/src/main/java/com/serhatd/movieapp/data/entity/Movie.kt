package com.serhatd.movieapp.data.entity

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("movie_id")
    var movie_id: Int,

    @SerializedName("movie_name")
    var movie_name: String,

    @SerializedName("movie_image")
    var movie_image: String,

    @SerializedName("movie_url")
    var movie_url: String
)