package com.task.ui.components.fragment.userdetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.usersdetails.UserDetailsData
import com.task.databinding.ItemUserDetailsBinding
import com.task.ui.components.callback.OnUserDataClickListener
import com.task.ui.components.fragment.userdetails.UserDetailsViewModel


class UserDetailsAdapter(
    private val context: Context,
    private val homeDetailViewModel: UserDetailsViewModel
) : RecyclerView.Adapter<UserDetailsViewHolder>() {

    private var userDetailsData: MutableList<UserDetailsData>? = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailsViewHolder {
        val itemBinding =
            ItemUserDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserDetailsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserDetailsViewHolder, position: Int) {
        holder.bind(
            context,
            position,
            onUserDataClickListener,
            userDetailsData
        )
    }

    fun bindData(userDetailsData: MutableList<UserDetailsData>) {
        this.userDetailsData = userDetailsData
        notifyDataSetChanged()
    }


    override
    fun getItemCount(): Int {
        return userDetailsData?.size ?: 0
    }

    private val onUserDataClickListener: OnUserDataClickListener =
        object : OnUserDataClickListener {
            override fun onClickUserDetails(position: Int, userDetailsData: UserDetailsData?) {
                homeDetailViewModel.getUserDetailData(userDetailsData)
            }
        }
}

