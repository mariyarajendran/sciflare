package com.task.ui.components.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.task.R
import com.task.databinding.FragmentHomeBinding
import com.task.ui.base.BaseFragment
import com.task.ui.components.activity.HomeActivity
import com.task.ui.components.fragment.home.adapter.HomeAdapter
import com.task.utils.SingleEvent
import com.task.utils.observeEvent
import com.task.utils.toGone
import com.task.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModelHome: HomeViewModel
    private var adapter: HomeAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        viewModelHome = ViewModelProvider(this)[HomeViewModel::class.java]
        observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = HomeAdapter(requireActivity(), viewModelHome)
        binding.rvHome.layoutManager = GridLayoutManager(activity, 2)
        binding.rvHome.adapter = adapter
    }


    override fun initOnClickListener() {
        binding.inlHomeHeader.imgSettingAppHeader.setOnClickListener(this)
        binding.btnFhRetry.setOnClickListener(this)
    }

    override fun init() {
    }

    override fun appHeaderAction() {
        binding.inlHomeHeader.tvTitleAppHeader.text =
            returnResString(R.string.app_name)
        binding.inlHomeHeader.imgCloseAppHeader.toGone()
        binding.inlHomeHeader.imgLeftArrowAppHeader.toGone()
        binding.inlHomeHeader.imgSettingAppHeader.toGone()
    }

    override fun observeViewModel() {
        observeEvent(viewModelHome.openHomeList, ::observerMainCategoryClickEvent)
    }

    private fun observerMainCategoryClickEvent(event: SingleEvent<Int?>) {
        event.getContentIfNotHandled()?.let { it ->
            val action =
                HomeFragmentDirections.actionHomeFragmentToSubCategoryFragment()
            Navigation.findNavController(binding.root).navigate(action)
            (activity as HomeActivity).bottomNavigationVisible(isVisible = false)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.inlHomeHeader.imgSettingAppHeader -> {

            }

            binding.btnFhRetry -> {
                isVisibleRetryBtn(isVisible = false)
            }
        }
    }

    private fun isVisibleRetryBtn(isVisible: Boolean) {
        if (isVisible) {
            binding.tvFhNoData.toVisible()
            binding.btnFhRetry.toVisible()
        } else {
            binding.tvFhNoData.toGone()
            binding.btnFhRetry.toGone()
        }
    }
}