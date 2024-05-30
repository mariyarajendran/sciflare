package com.task.ui.components.fragment.more.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.task.R
import com.task.data.dto.usersdetails.UserDetailsData
import com.task.databinding.FragmentUpdateUserDetailsBinding
import com.task.ui.base.BaseFragment
import com.task.ui.components.activity.HomeActivity
import com.task.utils.SingleEvent
import com.task.utils.showToast
import com.task.utils.toGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateUserDetailsFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentUpdateUserDetailsBinding
    private lateinit var profileViewModel: ProfileViewModel
    private val args: UpdateUserDetailsFragmentArgs by navArgs()
    private var userDetailsData = UserDetailsData()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateUserDetailsBinding.inflate(layoutInflater, container, false)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
        observeViewModel()
        bindUserDetails()
    }

    override fun initOnClickListener() {
        binding.inclFuudHeader.imgLeftArrowAppHeader.setOnClickListener(this)
        binding.btnFuudSubmit.setOnClickListener(this)
    }

    override fun init() {
        (activity as HomeActivity).bottomNavigationVisible(isVisible = false)
    }

    override fun appHeaderAction() {
        binding.inclFuudHeader.imgSettingAppHeader.toGone()
        binding.inclFuudHeader.imgCloseAppHeader.toGone()
        binding.inclFuudHeader.tvTitleAppHeader.text =
            requireActivity().resources.getString(R.string.profile)
    }

    override fun observeViewModel() {
        observeToast(profileViewModel.showToast)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.inclFuudHeader.imgLeftArrowAppHeader -> {
                backPressNavigation()
            }

            binding.btnFuudSubmit -> {
                val userDetailsData = UserDetailsData(
                    _id = userDetailsData._id,
                    name = binding.edtFuudUserName.text.toString(),
                    email = binding.edtFuudEmail.text.toString(),
                    mobile = binding.edtFuudMobileNo.text.toString(),
                    gender = userDetailsData.gender,
                )
                profileViewModel.updateUserDetailData(userDetailsData).apply {
                    profileViewModel.showToastMessage(requireActivity().resources.getString(R.string.user_details_updated_successfully))
                    backPressNavigation()
                }
            }
        }
    }

    private fun bindUserDetails() {
        userDetailsData = args.user ?: UserDetailsData()
        binding.edtFuudUserName.setText(userDetailsData.name)
        binding.edtFuudEmail.setText(userDetailsData.email)
        binding.edtFuudMobileNo.setText(userDetailsData.mobile)
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

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

}

