package com.serhatd.movieapp.ui.callback

import android.content.Context
import android.util.Log
import android.widget.Toast

class ApiCallback(private val context: Context) {
    fun onSuccess(code: Int, message: String) {
        Log.e("sa", "sa")
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun onError(code: Int, message: String) {
        Log.e("sa", "fragment error")
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}