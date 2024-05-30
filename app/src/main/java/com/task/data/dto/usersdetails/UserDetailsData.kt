package com.task.data.dto.usersdetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.task.utils.DatabaseConst
import java.io.Serializable

@Entity(tableName = DatabaseConst.USER_DETAILS_TABLE)
data class UserDetailsData(
    @PrimaryKey(autoGenerate = false)
    var _id: String = "",
    var name: String = "",
    var email: String = "",
    var mobile: String = "",
    var gender: String = "",
) : Serializable
