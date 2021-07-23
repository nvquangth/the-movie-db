package com.example.cleanarchitecture.ui.searchmovie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bt.presentation.base.model.Result
import com.bt.presentation.base.ui.BaseViewModel
import com.example.cleanarchitecture.domain.usecase.movie.SearchMovieUseCase
import com.example.cleanarchitecture.model.MovieItem
import com.example.cleanarchitecture.model.MovieItemMapper
import kotlinx.coroutines.launch

class SearchMovieViewModel @ViewModelInject constructor(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val movieItemMapper: MovieItemMapper
) : BaseViewModel() {

    val query = MutableLiveData<String>()

    val movieResults = MutableLiveData<Result<List<MovieItem>>>()

    fun searchMovie(q: String?) {
        if (!q.isNullOrEmpty()) {
            viewModelScope.launch {
                movieResults.postValue(Result.Loading)

                try {
                    val movies = searchMovieUseCase.execute(SearchMovieUseCase.Params(q, 1, true))
                        .map { movieItemMapper.mapToPresentation(it) }
                    movieResults.postValue(Result.Success(movies))
                } catch (e: Throwable) {
                    movieResults.postValue(Result.Error(e))
                }
            }
        }
    }

    fun clearQuery() {
        query.value = ""
    }
}
