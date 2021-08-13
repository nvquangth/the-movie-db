package com.example.cleanarchitecture.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.bt.presentation.base.model.Result
import com.bt.presentation.base.ui.BaseViewModel
import com.example.cleanarchitecture.anotation.DefaultDispatcher
import com.example.cleanarchitecture.domain.usecase.movie.GetDetailMovieUseCase
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.model.MovieItem
import com.example.cleanarchitecture.model.MovieItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val movieItemMapper: MovieItemMapper,
    private val getDetailMovieUseCase: GetDetailMovieUseCase
) : BaseViewModel() {

    private val _movieId = MutableLiveData<Int>()

    val movie: LiveData<Result<MovieItem>> = _movieId.switchMap {
        liveData(defaultDispatcher) {
            emit(Result.Loading)
            delay(5000L)

            try {
                getDetailMovieUseCase.execute(
                    GetDetailMovieUseCase.Params(
                        movieId = it,
                        fromServer = true
                    )
                ).collect {
                    emit(Result.Success(movieItemMapper.mapToPresentation(it)))
                }
            } catch (e: CleanException) {
                emit(Result.Error(e))
            }
        }
    }

    fun setMovieId(movieId: Int) {
        _movieId.value = movieId
    }
}
