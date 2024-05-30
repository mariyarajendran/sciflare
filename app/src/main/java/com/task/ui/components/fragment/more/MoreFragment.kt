package com.task.ui.components.fragment.more

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.R
import com.task.data.dto.more.MoreDataResponse
import com.task.databinding.FragmentMoreBinding
import com.task.ui.base.BaseFragment
import com.task.ui.components.activity.HomeActivity
import com.task.ui.components.fragment.more.adapter.MoreAdapter
import com.task.utils.EnumMore
import com.task.utils.SingleEvent
import com.task.utils.observeEvent
import com.task.utils.toGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreFragment : BaseFragment() {
    private lateinit var moreViewModel: MoreViewModel
    private lateinit var binding: FragmentMoreBinding
    private var moreAdapter: MoreAdapter? = null
    private var moreDataResponseList: MutableList<MoreDataResponse>? = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        moreViewModel = ViewModelProvider(this)[MoreViewModel::class.java]
        binding = FragmentMoreBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(requireActivity())
        var moreDataResponse6: MoreDataResponse? = null
        binding.rvFmMore.layoutManager = layoutManager
        val moreDataResponse1 = MoreDataResponse(
            title = requireActivity().resources.getString(R.string.profile),
            EnumMore.PROFILE.toString()
        )
        val moreDataResponse2 = MoreDataResponse(
            title = requireActivity().resources.getString(R.string.privacy_policy),
            EnumMore.PRIVACY.toString()
        )
        val moreDataResponse3 = MoreDataResponse(
            title = requireActivity().resources.getString(R.string.terms_and_conditions),
            EnumMore.TERMS.toString()
        )
        val moreDataResponse4 = MoreDataResponse(
            title = requireActivity().resources.getString(R.string.logout),
            EnumMore.LOGOUT.toString()
        )
        moreDataResponseList?.clear()
        moreDataResponseList?.add(moreDataResponse1)
        moreDataResponseList?.add(moreDataResponse2)
        moreDataResponseList?.add(moreDataResponse3)
        moreDataResponseList?.add(moreDataResponse4)
        moreAdapter = MoreAdapter(requireActivity(), moreDataResponseList, moreViewModel)
        binding.rvFmMore.adapter = moreAdapter
    }

    override fun initOnClickListener() {

    }

    override fun init() {

    }

    override fun appHeaderAction() {
        binding.inclFm.tvTitleAppHeader.text = requireActivity().resources.getString(R.string.more)
        binding.inclFm.imgCloseAppHeader.toGone()
        binding.inclFm.imgLeftArrowAppHeader.toGone()
        binding.inclFm.imgSettingAppHeader.toGone()
    }

    override fun observeViewModel() {
        observeEvent(moreViewModel.openMoreSelected, ::observerMoreClickEvent)
    }

    private fun observerMoreClickEvent(event: SingleEvent<MoreDataResponse?>) {
        event.getContentIfNotHandled()?.let { moreDataResponse ->
            when (moreDataResponse.code) {
                EnumMore.PROFILE.toString() -> {
                    (activity as HomeActivity).bottomNavigationVisible(isVisible = false)
                    val action = MoreFragmentDirections.actionMoreFragmentToProfileFragment()
                    Navigation.findNavController(binding.root).navigate(action)
                }

                EnumMore.LOGOUT.toString() -> {

                }

                else -> {}
            }
        }
    }
}