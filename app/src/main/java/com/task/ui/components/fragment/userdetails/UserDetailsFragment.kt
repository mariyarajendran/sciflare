package com.task.ui.components.fragment.userdetails

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
import com.task.R
import com.task.data.Resource
import com.task.data.dto.usersdetails.UserDetailsData
import com.task.databinding.UserDetailsFragmentBinding
import com.task.ui.base.BaseFragment
import com.task.ui.components.activity.HomeActivity
import com.task.ui.components.fragment.userdetails.adapter.UserDetailsAdapter
import com.task.utils.SingleEvent
import com.task.utils.observe
import com.task.utils.observeEvent
import com.task.utils.toGone
import com.task.utils.toVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: UserDetailsFragmentBinding
    private var userDetailsAdapter: UserDetailsAdapter? = null
    private val args: UserDetailsFragmentArgs by navArgs()
    private lateinit var viewModelDetail: UserDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = UserDetailsFragmentBinding.inflate(layoutInflater, container, false)
        viewModelDetail = ViewModelProvider(this)[UserDetailsViewModel::class.java]
        observeViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBackPressed()
        initRecyclerviewAdapter()
        initUserDataRecyclerAdapter()
        fetchUserDetailsApi()
    }

    override fun initOnClickListener() {
        binding.inlUsdfHeader.imgLeftArrowAppHeader.setOnClickListener(this)
        binding.inlUsdfHeader.imgCloseAppHeader.setOnClickListener(this)
    }

    override fun appHeaderAction() {
        binding.inlUsdfHeader.tvTitleAppHeader.text =
            requireActivity().resources?.getString(R.string.user_details)
        binding.inlUsdfHeader.imgCloseAppHeader.toGone()
        binding.inlUsdfHeader.imgSettingAppHeader.toGone()
        binding.inlUsdfHeader.imgLeftArrowAppHeader.toGone()
    }

    override fun init() {
    }

    override fun observeViewModel() {
        observe(viewModelDetail.userDetailsList, ::handleFetchUserDetailResult)
        observe(viewModelDetail.userDetailsListFromRoom, ::handleFetchUserDetailFromRoomResult)
        observeEvent(viewModelDetail.openUserDetail, ::observerUserDetailsClickEvent)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.inlUsdfHeader.imgLeftArrowAppHeader -> {
                backPressNavigation()
            }
        }
    }

    private fun initRecyclerviewAdapter() {
        val layoutManager = LinearLayoutManager(activity)
        binding.rvUdfUserDetails.layoutManager = layoutManager
    }

    private fun initUserDataRecyclerAdapter() {
        userDetailsAdapter = UserDetailsAdapter(requireActivity(), viewModelDetail)
        binding.rvUdfUserDetails.adapter = userDetailsAdapter
    }

    private fun fetchUserDetailsApi() {
        viewModelDetail.fetchUserDetailsFromRoom()
    }

    /**
     * Live data is fetched from the server.
     * */
    private fun handleFetchUserDetailResult(status: Resource<MutableList<UserDetailsData>>) {
        when (status) {
            is Resource.Loading -> {
                binding.pbUdfLoader.toVisible()
            }

            is Resource.Success -> status.data?.let {
                binding.pbUdfLoader.toGone()
                if (it.isNotEmpty()) {
                    userDetailsAdapter?.bindData(it)
                    binding.tvUdfNoData.toGone()
                } else {
                    binding.tvUdfNoData.toVisible()
                }
            }

            is Resource.DataError -> {
                binding.pbUdfLoader.toGone()
                binding.tvUdfNoData.toVisible()
                if (status.errorCode == -1) {
                    binding.tvUdfNoData.text =
                        requireActivity().resources.getString(R.string.no_internet)
                } else {
                    binding.tvUdfNoData.text =
                        requireActivity().resources.getString(R.string.server_error)
                }
            }

            else -> {
                binding.pbUdfLoader.toGone()
            }
        }
    }

    /**
     * Live data is fetched from the Room DB.
     * */
    private fun handleFetchUserDetailFromRoomResult(status: Resource<MutableList<UserDetailsData>>) {
        when (status) {
            is Resource.Loading -> {
                binding.pbUdfLoader.toVisible()
            }

            is Resource.Success -> status.data?.let {
                binding.pbUdfLoader.toGone()
                if (it.isNotEmpty()) {
                    userDetailsAdapter?.bindData(it)
                    binding.tvUdfNoData.toGone()
                } else {
                    binding.tvUdfNoData.toVisible()
                }
            }

            else -> {
                binding.pbUdfLoader.toGone()
            }
        }
    }

    private fun observerUserDetailsClickEvent(singleEvent: SingleEvent<UserDetailsData?>) {
        singleEvent.getContentIfNotHandled()?.let {
            (activity as HomeActivity).bottomNavigationVisible(isVisible = false)
            val action =
                UserDetailsFragmentDirections.actionUserDetailsFragmentToProfileFragment(user = it)
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