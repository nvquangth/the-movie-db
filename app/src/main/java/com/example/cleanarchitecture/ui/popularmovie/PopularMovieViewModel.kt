package com.example.cleanarchitecture.ui.popularmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.bt.presentation.base.model.Result
import com.bt.presentation.base.ui.BaseViewModel
import com.bt.presentation.base.utils.Event
import com.example.cleanarchitecture.anotation.DefaultDispatcher
import com.example.cleanarchitecture.domain.usecase.movie.GetPopularMovieUseCase
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.model.MovieItem
import com.example.cleanarchitecture.model.MovieItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class PopularMovieViewModel @Inject constructor(
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val movieItemMapper: MovieItemMapper,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _getPopularMovieEvent = MutableLiveData<Event<Unit>>()

    private var page: Int = 0

    val movieResults: LiveData<Result<List<MovieItem>>> = _getPopularMovieEvent.switchMap {
        liveData(defaultDispatcher) {
            emit(Result.Loading)

            try {
                val movies = getPopularMovieUseCase.execute(GetPopularMovieUseCase.Params(page))
                    .map { movieItemMapper.mapToPresentation(it) }
                emit(Result.Success(movies))
            } catch (e: CleanException) {
                emit(Result.Error(e))
                setExceptionAsync(e)
            }
        }
    }

    fun getPopularMovie(page: Int) {
        this.page = page
        _getPopularMovieEvent.postValue(Event.EMPTY_EVENT)
    }
}
