package com.task.data.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.task.data.dto.usersdetails.UserDetailsData
import com.task.data.room.Converters
import com.task.utils.DatabaseConst


@Database(
    entities = [
        UserDetailsData::class,
    ],
    version = DatabaseConst.DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun roomDao(): RoomDao
}