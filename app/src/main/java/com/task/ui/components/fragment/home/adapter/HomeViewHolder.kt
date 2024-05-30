package com.task.ui.components.fragment.home.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.task.databinding.ItemHomeBinding
import com.task.ui.components.callback.OnItemTapCallback

class HomeViewHolder(private val itemBinding: ItemHomeBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(
        context: Context,
        position: Int,
        onItemTapCallback: OnItemTapCallback
    ) {

        itemBinding.crdItemHome.setOnClickListener {
            onItemTapCallback.onItemTap(position)
        }
    }
}
