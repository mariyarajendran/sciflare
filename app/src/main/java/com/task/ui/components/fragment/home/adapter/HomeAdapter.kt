package com.task.ui.components.fragment.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.databinding.ItemHomeBinding
import com.task.ui.components.callback.OnItemTapCallback
import com.task.ui.components.fragment.home.HomeViewModel

class HomeAdapter(
    private val context: Context,
    private val homeViewModel: HomeViewModel
) : RecyclerView.Adapter<HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemBinding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
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

