package com.task.ui.components.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.task.databinding.ActivitySplashBinding
import com.task.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun initViewBinding() {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun observeViewModel() {

    }

    override fun init() {
        redirectToHome()
    }

    private fun redirectToHome() {
        Handler().postDelayed(Runnable {
            val mainIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            this@SplashActivity.startActivity(mainIntent)
            this@SplashActivity.finish()
        }, 2000)
    }

}