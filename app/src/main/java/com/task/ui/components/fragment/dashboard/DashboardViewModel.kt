package com.task.ui.components.fragment.dashboard

import android.content.Context
import com.task.data.DataRepositorySource
import com.task.data.local.LocalData
import com.task.ui.base.BaseViewModel
import com.task.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val mDataRepositoryRepository: DataRepositorySource,
    private val mLocalData: LocalData,
    @ApplicationContext context: Context
) : BaseViewModel() {

    @SuppressWarnings("VisibleForTests")
    override fun showToastMessage(error: String) {
        showToastPrivate.value = SingleEvent(error)
    }
}