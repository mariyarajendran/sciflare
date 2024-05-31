package com.task.ui.components.activity

import android.content.Intent
import android.os.Bundle
import com.task.databinding.ActivitySplashBinding
import com.task.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    @OptIn(DelicateCoroutinesApi::class)
    private fun redirectToHome() {
        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            val mainIntent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }

}