package com.task.ui.base

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.task.DEFAULT_COUNTRY_CODE
import com.task.PREF_DEFAULT_COUNTRY_CODE_KEY
import com.task.PREF_PREFERENCES_FILE_NAME
import com.task.data.local.LocalData
import java.util.Locale
import javax.inject.Inject


abstract class BaseActivity : AppCompatActivity() {
    abstract fun observeViewModel()
    protected abstract fun initViewBinding()
    protected abstract fun init()

    @Inject
    lateinit var localRepository: LocalData

    companion object {
        var dLocale: Locale? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        initViewBinding()
        observeViewModel()
    }


    override fun attachBaseContext(newBase: Context?) {
        if (dLocale == null) {
            dLocale = Locale(DEFAULT_COUNTRY_CODE)
        }
        dLocale = newBase?.let { getCountryCode(it) }?.let { Locale(it) }
        val localeUpdatedContext: ContextWrapper =
            com.task.utils.ContextUtils.updateLocale(newBase, dLocale)
        super.attachBaseContext(localeUpdatedContext)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    private fun getCountryCode(context: Context?): String? {
        val sharedPref = context?.getSharedPreferences(PREF_PREFERENCES_FILE_NAME, 0)
        return sharedPref?.getString(PREF_DEFAULT_COUNTRY_CODE_KEY, DEFAULT_COUNTRY_CODE)
    }
}
