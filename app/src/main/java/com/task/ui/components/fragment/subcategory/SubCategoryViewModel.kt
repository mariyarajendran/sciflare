package com.task.ui.components.fragment.subcategory

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.DataRepositorySource
import com.task.data.local.LocalData
import com.task.data.room.dao.RoomDatabase
import com.task.ui.base.BaseViewModel
import com.task.utils.SingleEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubCategoryViewModel @Inject constructor(
    private val mDataRepositoryRepository: DataRepositorySource,
    private val mLocalData: LocalData,
    private val db: RoomDatabase,
) :
    BaseViewModel() {
    private val dao = db.roomDao()

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private val openHomeDetailListPrivate = MutableLiveData<SingleEvent<Int>>()
    val openHomeDetailList: LiveData<SingleEvent<Int>> get() = openHomeDetailListPrivate



}