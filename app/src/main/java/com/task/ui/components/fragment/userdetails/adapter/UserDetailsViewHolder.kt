package com.task.ui.components.fragment.userdetails.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.usersdetails.UserDetailsData
import com.task.databinding.ItemUserDetailsBinding
import com.task.ui.components.callback.OnUserDataClickListener

class UserDetailsViewHolder(private val itemBinding: ItemUserDetailsBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(
        context: Context,
        position: Int,
        onUserDataClickListener: OnUserDataClickListener,
        userDetailsData: MutableList<UserDetailsData>?
    ) {
        itemBinding.tvIudUserNameData.text = userDetailsData?.get(position)?.name ?: ""
        itemBinding.tvIudEmailData.text = userDetailsData?.get(position)?.email ?: ""
        itemBinding.tvIudMobileNoData.text = userDetailsData?.get(position)?.mobile ?: ""
        itemBinding.tvIudGenderData.text = userDetailsData?.get(position)?.gender ?: ""
        itemBinding.crdIud.setOnClickListener {
            onUserDataClickListener.onClickUserDetails(position, userDetailsData?.get(position))
        }
    }
}