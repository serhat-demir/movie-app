package com.serhatd.movieapp.data.entity

import com.google.gson.annotations.SerializedName

data class ApiResponseWithData<T>(
    @SerializedName("code")
    var code: Int,

    @SerializedName("message")
    var message: String,

    @SerializedName("data")
    var data: T?
)