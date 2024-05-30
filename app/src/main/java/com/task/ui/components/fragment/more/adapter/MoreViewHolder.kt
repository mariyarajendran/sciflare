package com.task.ui.components.fragment.more.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.task.data.dto.more.MoreDataResponse
import com.task.databinding.ItemMoreBinding
import com.task.ui.components.callback.OnItemTapCallback

class MoreViewHolder(private val itemBinding: ItemMoreBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(
        context: Context,
        moreDataResponse: MutableList<MoreDataResponse>?,
        position: Int,
        onItemTapCallback: OnItemTapCallback
    ) {
        itemBinding.tvImTitle.apply {
            text = moreDataResponse?.get(position)?.title ?: ""
        }

        itemBinding.cslIm.setOnClickListener {
            onItemTapCallback.onItemTap(position)
        }
    }
}
