package com.task.ui.components.fragment.more.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.task.R
import com.task.databinding.FragmentProfileBinding
import com.task.ui.base.BaseFragment
import com.task.ui.components.activity.HomeActivity
import com.task.utils.toGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
        observeViewModel()
    }


    override fun initOnClickListener() {
        binding.inclFp.imgLeftArrowAppHeader.setOnClickListener(this)
        binding.btnFpSubmit.setOnClickListener(this)
    }

    override fun init() {
        (activity as HomeActivity).bottomNavigationVisible(isVisible = false)
    }

    override fun appHeaderAction() {
        binding.inclFp.imgSettingAppHeader.toGone()
        binding.inclFp.imgCloseAppHeader.toGone()
        binding.inclFp.tvTitleAppHeader.text =
            requireActivity().resources.getString(R.string.profile)
    }

    override fun observeViewModel() {

    }

    override fun onClick(v: View?) {
        when (v) {
            binding.inclFp.imgLeftArrowAppHeader -> {
                backPressNavigation()
            }

            binding.btnFpSubmit -> {

            }
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

