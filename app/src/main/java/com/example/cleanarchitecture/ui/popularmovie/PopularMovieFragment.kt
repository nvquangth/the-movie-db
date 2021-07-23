package com.example.cleanarchitecture.ui.popularmovie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.bt.presentation.base.model.Result
import com.bt.presentation.base.model.RetryCallback
import com.bt.presentation.base.ui.BaseFragment
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentPopularMovieBinding
import com.example.cleanarchitecture.model.MovieItem
import com.example.cleanarchitecture.ui.main.MainSharedViewModel
import com.example.cleanarchitecture.widget.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_popular_movie.*

@AndroidEntryPoint
class PopularMovieFragment : BaseFragment<FragmentPopularMovieBinding, PopularMovieViewModel>() {
    override val viewModel: PopularMovieViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_popular_movie

    private val sharedViewModel: MainSharedViewModel by activityViewModels()

    private var movieAdapter: MovieAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(::onItemMovieClick)
        recyclerMovie.apply {
            addItemDecoration(SpacesItemDecoration(requireContext()))
            adapter = movieAdapter
        }

        viewBinding.callback = object : RetryCallback {
            override fun retry() {
                viewModel.getPopularMovie(page = 1)
            }
        }

        with(viewModel) {
            movieResults.observe(viewLifecycleOwner) {

                viewBinding.result = it

                when (it) {
                    is Result.Loading -> {
                    }
                    is Result.Error -> {
                    }
                    else -> {
                        val movies = it.data

                        movieAdapter?.submitList(movies?.toMutableList())
                    }
                }
            }

            getPopularMovie(page = 1)
        }

        toolbar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.searchAction -> {
                    findNavController().navigate(PopularMovieFragmentDirections.actionPopularMovieFragmentToSearchMovieFragment())
                    true
                }
                R.id.logoutAction -> {
                    sharedViewModel.logout()

                    true
                }
                R.id.changThemeAction -> {
                    findNavController().navigate(PopularMovieFragmentDirections.actionPopularMovieFragmentToThemeSettingFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun onItemMovieClick(movie: MovieItem?) {
        movie?.let {
            findNavController().navigate(
                PopularMovieFragmentDirections.actionPopularMovieFragmentToDetailMovieActivity(
                    movieId = it.id
                )
            )
        }
    }
}
