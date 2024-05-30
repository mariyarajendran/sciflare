package com.task.ui.components.fragment.registration

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.DataRepositorySource
import com.task.data.local.LocalData
import com.task.ui.base.BaseViewModel
import com.task.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val mDataRepositoryRepository: DataRepositorySource,
    private val mLocalData: LocalData,
    @ApplicationContext context: Context
) : BaseViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate


    fun showFailureToastMessage(error: String) {
        showToastPrivate.value = SingleEvent(error)
    }

    fun storeRegisterSession(status: Boolean) {
        mLocalData.putRegisterSession(status)
    }

}