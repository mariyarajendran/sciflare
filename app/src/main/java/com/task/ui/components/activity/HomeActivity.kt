package com.task.ui.components.activity

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.task.R
import com.task.databinding.ActivityHomeBinding
import com.task.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpNavController()
    }

    override fun observeViewModel() {

    }

    override fun initViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun init() {
    }

    private fun setUpNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_home_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bnAhHome.setupWithNavController(navController)
    }


    fun bottomNavigationVisible(isVisible: Boolean) {
        binding.bnAhHome.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}