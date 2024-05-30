package com.task.ui.components.fragment.subcategory.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.databinding.ItemSubCategoryBinding
import com.task.ui.components.callback.OnItemTapCallback
import com.task.ui.components.fragment.subcategory.SubCategoryViewModel


class SubCategoryAdapter(
    private val context: Context,
    private val homeDetailViewModel: SubCategoryViewModel
) : RecyclerView.Adapter<SubCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryViewHolder {
        val itemBinding =
            ItemSubCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubCategoryViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SubCategoryViewHolder, position: Int) {
        holder.bind(
            context,
            position,
            onItemTapCallback
        )
    }

    override fun getItemCount(): Int {
        return 8
    }

    private val onItemTapCallback: OnItemTapCallback = object : OnItemTapCallback {
        override fun onItemTap(position: Int) {
        }
    }
}

