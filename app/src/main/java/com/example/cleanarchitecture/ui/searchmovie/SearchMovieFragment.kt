package com.example.cleanarchitecture.ui.searchmovie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bt.presentation.base.extension.hideKeyBoard
import com.bt.presentation.base.extension.hideLoadingDialog
import com.bt.presentation.base.extension.showLoadingDialog
import com.bt.presentation.base.model.Result
import com.bt.presentation.base.model.RetryCallback
import com.bt.presentation.base.ui.BaseFragment
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentSearchMovieBinding
import com.example.cleanarchitecture.model.MovieItem
import com.example.cleanarchitecture.ui.popularmovie.MovieAdapter
import com.example.cleanarchitecture.widget.SpacesItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMovieFragment : BaseFragment<FragmentSearchMovieBinding, SearchMovieViewModel>() {

    override val viewModel: SearchMovieViewModel by viewModels()

    override val layoutRes: Int = R.layout.fragment_search_movie

    private var movieAdapter: MovieAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
            hideKeyBoard()
        }

        viewBinding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.clearAction -> {
                    viewModel.clearQuery()
                    true
                }
                else -> false
            }
        }

        movieAdapter = MovieAdapter(::onItemMovieClick)
        viewBinding.recyclerMovie.apply {
            addItemDecoration(SpacesItemDecoration(requireContext()))
            adapter = movieAdapter
        }

        viewBinding.callback = object : RetryCallback {

            override fun retry() {
                viewModel.searchMovie(viewModel.query.value)
            }
        }

        with(viewModel) {
            query.observe(viewLifecycleOwner) {
                searchMovie(it)
            }

            movieResults.observe(viewLifecycleOwner) {

                viewBinding.result = it

                when (it) {
                    is Result.Loading -> {
                        context.showLoadingDialog()
                    }

                    is Result.Success -> {
                        context.hideLoadingDialog()

                        val movies = it.data

                        movieAdapter?.submitList(movies.toMutableList())
                    }

                    is Result.Error -> {
                        context.hideLoadingDialog()
                    }
                }
            }
        }
    }

    private fun onItemMovieClick(movie: MovieItem?) {
    }
}
