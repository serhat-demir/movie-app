package com.serhatd.movieapp.data.prefs

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs {
    companion object {
        const val COL_USER_ID = "user_id"
        const val COL_USER_NAME = "user_name"
        const val COL_USER_PASSWORD = "user_password"

        fun setSharedPreference(context: Context, data: HashMap<String, String>) {
            val sharedPref: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val edit = sharedPref.edit()

            for ((key, value) in data) {
                edit.putString(key, value)
            }

            edit.apply()
        }

        fun getSharedPreference(context: Context, key: String, defaultValue: String): String {
            return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE).getString(key, defaultValue) ?: defaultValue
        }

        fun removeSharedPreference(context: Context, keys: Array<String>) {
            val sharedPref: SharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val edit = sharedPref.edit()

            for (key in keys) {
                edit.remove(key)
            }

            edit.apply()
        }
    }
}