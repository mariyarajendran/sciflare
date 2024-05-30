package com.task.ui.components.callback

import com.task.data.dto.usersdetails.UserDetailsData

interface OnUserDataClickListener {
    fun onClickUserDetails(position: Int, userDetailsData: UserDetailsData?)
}