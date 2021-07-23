package com.example.cleanarchitecture.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bt.presentation.base.extension.mapToCleanException
import com.bt.presentation.base.ui.BaseViewModel
import com.bt.presentation.base.utils.Event
import com.example.cleanarchitecture.anotation.DefaultDispatcher
import com.example.cleanarchitecture.domain.usecase.auth.LogoutUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainSharedViewModel @ViewModelInject constructor(
    private val logoutUseCase: LogoutUseCase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _logoutEvent = MutableLiveData<Event<Unit>>()
    val logoutEvent: LiveData<Event<Unit>>
        get() = _logoutEvent

    fun logout() {
        viewModelScope.launch(defaultDispatcher) {
            runCatching {
                logoutUseCase.execute()
                _logoutEvent.postValue(Event(Unit))
            }.getOrElse {
                it.mapToCleanException().apply {
                    setExceptionAsync(this)
                }
            }
        }
    }
}
