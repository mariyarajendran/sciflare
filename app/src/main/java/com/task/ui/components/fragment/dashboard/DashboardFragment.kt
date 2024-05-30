package com.task.ui.components.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.task.databinding.FragmentDashboardBinding
import com.task.ui.base.BaseFragment
import com.task.ui.components.activity.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment(), View.OnClickListener {
    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        dashboardViewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun initOnClickListener() {

    }

    override fun init() {
        (activity as HomeActivity).bottomNavigationVisible(isVisible = true)
    }

    override fun appHeaderAction() {

    }

    override fun observeViewModel() {

    }

    override fun onClick(v: View?) {

    }


}