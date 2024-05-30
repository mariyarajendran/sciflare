package com.task.ui.components.fragment.subcategory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.databinding.SubCategoryFragmentBinding
import com.task.ui.base.BaseFragment
import com.task.ui.components.activity.HomeActivity
import com.task.ui.components.fragment.subcategory.adapter.SubCategoryAdapter
import com.task.utils.SingleEvent
import com.task.utils.observeEvent
import com.task.utils.toGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubCategoryFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: SubCategoryFragmentBinding
    private var subCategoryAdapter: SubCategoryAdapter? = null
    private val args: SubCategoryFragmentArgs by navArgs()
    private lateinit var viewModelDetail: SubCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = SubCategoryFragmentBinding.inflate(layoutInflater, container, false)
        viewModelDetail = ViewModelProvider(this)[SubCategoryViewModel::class.java]
        initRecyclerviewAdapter()
        observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
        subCategoryAdapter = SubCategoryAdapter(requireActivity(), viewModelDetail)
        binding.rvHomeDetail.adapter = subCategoryAdapter
    }


    override fun initOnClickListener() {
        binding.inlHomeDetailHeader.imgLeftArrowAppHeader.setOnClickListener(this)
        binding.inlHomeDetailHeader.imgCloseAppHeader.setOnClickListener(this)
    }

    override fun init() {
    }

    override fun appHeaderAction() {
        binding.inlHomeDetailHeader.tvTitleAppHeader.text = "Android Course Package"
        binding.inlHomeDetailHeader.imgCloseAppHeader.toGone()
        binding.inlHomeDetailHeader.imgSettingAppHeader.toGone()
        binding.inlHomeDetailHeader.imgLeftArrowAppHeader.toGone()
    }

    override fun observeViewModel() {
        observeEvent(viewModelDetail.openHomeDetailList, ::observerHomeDetailClickEvent)
    }

    private fun initRecyclerviewAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvHomeDetail.layoutManager = layoutManager
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.inlHomeDetailHeader.imgLeftArrowAppHeader -> {
                backPressNavigation()
            }
        }
    }

    private fun observerHomeDetailClickEvent(event: SingleEvent<Int>) {
        event.getContentIfNotHandled()?.let { id ->
            val action =
                SubCategoryFragmentDirections.actionSubCategoryFragmentToVideoFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun backPressNavigation() {
        findNavController().navigateUp()
        (activity as HomeActivity).bottomNavigationVisible(isVisible = true)
    }

    private fun onBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backPressNavigation()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }
}