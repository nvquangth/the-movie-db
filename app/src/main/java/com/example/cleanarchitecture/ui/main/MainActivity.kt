package com.example.cleanarchitecture.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.bt.presentation.base.ui.BaseActivity
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override val layoutRes: Int = R.layout.activity_main

    override val sharedViewModel: MainSharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(sharedViewModel) {
            logoutEvent.observe(this@MainActivity) {
                it.getContentIfNotHandled()?.let {
                    navigateToLogin()
                }
            }
        }
    }

    private fun navigateToLogin() {
        findNavController(R.id.nav_host_fragment).navigate(R.id.actionLogout)
    }
}
