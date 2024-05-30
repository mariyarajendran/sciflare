package com.task.ui.components.fragment.userdetails

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.data.DataRepositorySource
import com.task.data.Resource
import com.task.data.dto.usersdetails.UserDetailsData
import com.task.data.room.dao.RoomDatabase
import com.task.ui.base.BaseViewModel
import com.task.utils.SingleEvent
import com.task.utils.wrapEspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val mDataRepositoryRepository: DataRepositorySource,
    private val db: RoomDatabase,
) : BaseViewModel() {
    private val dao = db.roomDao()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val getAllUserDetailsPrivate = MutableLiveData<Resource<MutableList<UserDetailsData>>>()
    val userDetailsList: LiveData<Resource<MutableList<UserDetailsData>>> get() = getAllUserDetailsPrivate

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val getAllUserDetailsFromRoomPrivate =
        MutableLiveData<Resource<MutableList<UserDetailsData>>>()
    val userDetailsListFromRoom: LiveData<Resource<MutableList<UserDetailsData>>> get() = getAllUserDetailsFromRoomPrivate


    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openUserDetailPrivate = MutableLiveData<SingleEvent<UserDetailsData?>>()
    val openUserDetail: LiveData<SingleEvent<UserDetailsData?>> get() = openUserDetailPrivate

    fun fetchUserDetailsFromRoom() {
        viewModelScope.launch {
            dao.getUserDetailsData().let { data ->
                dao.isEmptyUserDetailsData().let { isEmpty ->
                    if (!isEmpty) {
                        getAllUserDetailsPrivate.value = Resource.Success(data)
                    } else {
                        fetchUserDetails()
                    }
                }
            }
        }
    }

    private fun fetchUserDetails() {
        viewModelScope.launch {
            getAllUserDetailsPrivate.value = Resource.Loading()
            wrapEspressoIdlingResource {
                mDataRepositoryRepository.fetchUserDetails().collect { data ->
                    @Suppress("UNCHECKED_CAST")
                    getAllUserDetailsPrivate.value = data as Resource<MutableList<UserDetailsData>>?
                }
            }
        }
    }

    fun getUserDetailData(userDetailsData: UserDetailsData?) {
        openUserDetailPrivate.value = SingleEvent(userDetailsData)
    }

}