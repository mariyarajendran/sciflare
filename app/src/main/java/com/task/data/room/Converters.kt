package com.task.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.data.dto.usersdetails.UserDetailsData

class Converters {
    @TypeConverter
    fun fromJsonUserDetailsData(data: String): MutableList<UserDetailsData> {
        val type = object : TypeToken<MutableList<UserDetailsData>>() {}.type
        return Gson().fromJson(data, type)
    }

    @TypeConverter
    fun toJsonUserDetailsData(data: MutableList<UserDetailsData>): String {
        val type = object : TypeToken<MutableList<UserDetailsData>>() {}.type
        return Gson().toJson(data, type)
    }

}