package com.task.ui.components.fragment.more

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.dto.more.MoreDataResponse
import com.task.ui.base.BaseViewModel
import com.task.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor() : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openMorePrivate = MutableLiveData<SingleEvent<MoreDataResponse?>>()
    val openMoreSelected: LiveData<SingleEvent<MoreDataResponse?>> get() = openMorePrivate

    fun onMoreSelected(
        moreDataResponse: MutableList<MoreDataResponse>?, position: Int
    ) {
        openMorePrivate.value = SingleEvent(moreDataResponse?.get(position))
    }

}