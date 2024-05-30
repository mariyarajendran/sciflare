package com.task.ui.components.fragment.more.profile

import com.task.data.local.LocalData
import com.task.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private var localData: LocalData
) : BaseViewModel() {


}