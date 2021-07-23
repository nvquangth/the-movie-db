package com.example.cleanarchitecture.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.bt.presentation.base.ui.BaseActivity
import com.bt.presentation.base.ui.BaseViewModel
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailActivityViewModel>() {

    override val viewModel: DetailActivityViewModel by viewModels()

    override val layoutRes: Int = R.layout.activity_detail

    override val sharedViewModel: BaseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findNavController(R.id.nav_host_fragment)
            .setGraph(R.navigation.detail_nav, intent.extras)
    }
}
