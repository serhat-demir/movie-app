package com.serhatd.movieapp.ui.callback

interface ApiCallback {
    fun onSuccess(code: Int, message: String)
    fun onError(code: Int, message: String)
}