package com.serhatd.movieapp.data.entity

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("user_id")
    var user_id: Int,

    @SerializedName("user_name")
    var user_name: String,

    @SerializedName("user_password")
    var user_password: String
)