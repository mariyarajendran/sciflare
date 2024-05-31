package com.task.ui.components.fragment.more.profile

import androidx.lifecycle.viewModelScope
import com.task.data.dto.usersdetails.UserDetailsData
import com.task.data.room.dao.RoomDatabase
import com.task.ui.base.BaseViewModel
import com.task.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val db: RoomDatabase,
) : BaseViewModel() {
    private val dao = db.roomDao()

    fun updateUserDetailData(userDetailsData: UserDetailsData) {
        viewModelScope.launch {
            dao.userDetailData(userDetailsData)
        }
    }

    @SuppressWarnings("VisibleForTests")
    override fun showToastMessage(error: String) {
        showToastPrivate.value = SingleEvent(error)
    }
}