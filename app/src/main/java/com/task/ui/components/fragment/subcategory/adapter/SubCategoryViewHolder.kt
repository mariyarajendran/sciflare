package com.task.ui.components.fragment.subcategory.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.task.databinding.ItemSubCategoryBinding
import com.task.ui.components.callback.OnItemTapCallback

class SubCategoryViewHolder(private val itemBinding: ItemSubCategoryBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(
        context: Context,
        position: Int,
        onItemTapCallback: OnItemTapCallback
    ) {
        itemBinding.crdItemHomeDetail.setOnClickListener {
            onItemTapCallback.onItemTap(position)
        }
    }
}