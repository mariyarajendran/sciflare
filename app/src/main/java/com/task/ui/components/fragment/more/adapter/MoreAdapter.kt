package com.task.ui.components.fragment.more.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.more.MoreDataResponse
import com.task.databinding.ItemMoreBinding
import com.task.ui.components.callback.OnItemTapCallback
import com.task.ui.components.fragment.more.MoreViewModel

class MoreAdapter(
    private val context: Context,
    private var moreDataResponse: MutableList<MoreDataResponse>?,
    private val moreViewModel: MoreViewModel
) : RecyclerView.Adapter<MoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreViewHolder {
        val itemBinding =
            ItemMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoreViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MoreViewHolder, position: Int) {
        holder.bind(
            context, moreDataResponse, position, onItemTapCallback
        )
    }

    override fun getItemCount(): Int {
        return moreDataResponse?.size ?: 0
    }

    private val onItemTapCallback: OnItemTapCallback = object : OnItemTapCallback {
        override fun onItemTap(position: Int) {
            moreViewModel.onMoreSelected(moreDataResponse, position)
        }
    }
}

