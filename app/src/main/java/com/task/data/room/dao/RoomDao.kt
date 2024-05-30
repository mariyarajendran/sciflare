package com.task.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.data.dto.usersdetails.UserDetailsData
import com.task.utils.DatabaseConst
import kotlinx.coroutines.flow.Flow


@Dao
interface RoomDao {

    /**
     * User Details Data table with operations (User Details Table)
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetailsData(posts: MutableList<UserDetailsData>)

    @Query("DELETE FROM ${DatabaseConst.USER_DETAILS_TABLE}")
    suspend fun deleteUserDetailsData()

    @Query("SELECT * FROM ${DatabaseConst.USER_DETAILS_TABLE}")
    fun getUserDetailsData(): Flow<MutableList<UserDetailsData>>

    @Query("SELECT (SELECT COUNT(*) FROM ${DatabaseConst.USER_DETAILS_TABLE}) == 0")
    fun isEmptyUserDetailsData(): Flow<Boolean>

}