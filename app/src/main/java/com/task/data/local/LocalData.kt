package com.task.data.local

import android.content.Context
import com.task.*
import javax.inject.Inject


class LocalData @Inject constructor(val context: Context) {


    fun putRegisterSession(status: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_PREFERENCES_FILE_NAME, 0)
        sharedPref.edit().putBoolean(PREF_IS_REGISTER_KEY, status).apply()
    }


    fun getCountryCode(): String? {
        val sharedPref = context.getSharedPreferences(PREF_PREFERENCES_FILE_NAME, 0)
        return sharedPref.getString(PREF_DEFAULT_COUNTRY_CODE_KEY, DEFAULT_COUNTRY_CODE)
    }


}

