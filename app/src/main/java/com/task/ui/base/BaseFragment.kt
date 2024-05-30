package com.task.ui.base


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.task.data.local.LocalData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    abstract fun initOnClickListener()
    abstract fun init()
    abstract fun appHeaderAction()
    abstract fun observeViewModel()

    @Inject
    lateinit var localRepository: LocalData
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initOnClickListener()
        appHeaderAction()
    }

    override fun onResume() {
        super.onResume()
    }

    fun returnResString(id: Int): String? {
        return activity?.resources?.getString(id)
    }

}