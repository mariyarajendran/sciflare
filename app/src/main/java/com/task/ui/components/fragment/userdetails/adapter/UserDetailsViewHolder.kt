package com.task.ui.components.fragment.userdetails.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.usersdetails.UserDetailsData
import com.task.databinding.ItemUserDetailsBinding
import com.task.ui.components.callback.OnUserDataClickListener
import com.task.utils.EnumGender

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

        itemBinding.crdIud.setOnClickListener {
            onUserDataClickListener.onClickUserDetails(position, userDetailsData?.get(position))
        }
        when (userDetailsData?.get(position)?.gender ?: "") {
            EnumGender.M.toString() -> {
                itemBinding.tvIudGenderData.text = EnumGender.Male.toString()
            }

            EnumGender.F.toString() -> {
                itemBinding.tvIudGenderData.text = EnumGender.Female.toString()
            }

            EnumGender.O.toString() -> {
                itemBinding.tvIudGenderData.text = EnumGender.Other.toString()
            }

            else -> {
                itemBinding.tvIudGenderData.text = ""
            }
        }
    }
}