package com.task.ui.components.fragment.more

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.processphoenix.ProcessPhoenix
import com.task.R
import com.task.data.dto.more.MoreDataResponse
import com.task.databinding.FragmentMoreBinding
import com.task.ui.base.BaseFragment
import com.task.ui.components.activity.HomeActivity
import com.task.ui.components.fragment.more.adapter.MoreAdapter
import com.task.utils.EnumMore
import com.task.utils.SingleEvent
import com.task.utils.observeEvent
import com.task.utils.showToast
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
        binding.rvFmMore.layoutManager = layoutManager
        val moreDataResponse1 = MoreDataResponse(
            title = requireActivity().resources.getString(R.string.clear_cache),
            EnumMore.CACHE.toString()
        )
        val moreDataResponse2 = MoreDataResponse(
            title = requireActivity().resources.getString(R.string.privacy_policy),
            EnumMore.PRIVACY.toString()
        )
        val moreDataResponse3 = MoreDataResponse(
            title = requireActivity().resources.getString(R.string.terms_and_conditions),
            EnumMore.TERMS.toString()
        )
        moreDataResponseList?.clear()
        moreDataResponseList?.add(moreDataResponse1)
        moreDataResponseList?.add(moreDataResponse2)
        moreDataResponseList?.add(moreDataResponse3)
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
        observeToast(moreViewModel.showToast)
        observeEvent(moreViewModel.openMoreSelected, ::observerMoreClickEvent)
    }

    private fun observerMoreClickEvent(event: SingleEvent<MoreDataResponse?>) {
        event.getContentIfNotHandled()?.let { moreDataResponse ->
            when (moreDataResponse.code) {
                EnumMore.CACHE.toString() -> {
                    /**
                     * If you clear the cache, the local Room database will be deleted.
                     * The app will then fetch data from the API and store it in Room again,
                     * allowing you to easily test the flow as needed.
                     * */
                    clearRoomDatabaseData()
                }

                else -> {}
            }
        }
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun clearRoomDatabaseData() {
        (activity as HomeActivity).clearLocalRoomDb().apply {
            moreViewModel.showToastMessage(requireActivity().resources.getString(R.string.data_in_the_cache_has_been_cleared))
            restartProject()
        }
    }

    private fun restartProject() {
        val nextScreenIntent = Intent(requireActivity(), HomeActivity::class.java)
        ProcessPhoenix.triggerRebirth(requireActivity(), nextScreenIntent)
    }
}