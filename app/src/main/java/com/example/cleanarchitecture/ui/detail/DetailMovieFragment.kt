package com.example.cleanarchitecture.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.bt.presentation.base.ui.BaseFragment
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding, DetailMovieViewModel>() {

    override val viewModel: DetailMovieViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_detail_movie

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs by navArgs<DetailMovieFragmentArgs>()

        with(viewModel) {
            movie.observe(viewLifecycleOwner) {

                viewBinding.result = it
            }

            setMovieId(safeArgs.movieId)
        }
    }
}
