package com.task.ui.base

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.logging.ErrorManager
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val showToastPrivate = MutableLiveData<SingleEvent<Any>>()
    val showToast: LiveData<SingleEvent<Any>> get() = showToastPrivate

    open fun showToastMessage(error: String) {
        showToastPrivate.value = SingleEvent(error)
    }
}
