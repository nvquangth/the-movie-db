package com.example.cleanarchitecture.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.bt.presentation.base.extension.mapToCleanException
import com.bt.presentation.base.model.Result
import com.bt.presentation.base.model.Validation
import com.bt.presentation.base.ui.BaseViewModel
import com.bt.presentation.base.utils.Event
import com.example.cleanarchitecture.anotation.DefaultDispatcher
import com.example.cleanarchitecture.domain.usecase.auth.CheckLoginUseCase
import com.example.cleanarchitecture.domain.usecase.auth.LoginUseCase
import com.example.cleanarchitecture.domain.usecase.validation.ValidatePasswordUseCase
import com.example.cleanarchitecture.domain.usecase.validation.ValidateUsernameUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val validateUsernameUseCase: ValidateUsernameUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val loginUseCase: LoginUseCase,
    private val checkLoginUseCase: CheckLoginUseCase,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _loginResult = MutableLiveData<Event<Result<Boolean>>>()
    val loginResult: LiveData<Event<Result<Boolean>>>
        get() = _loginResult

    val username = MutableLiveData<String>()

    val usernameValidation: LiveData<Validation<String>> = username.switchMap {
        liveData(defaultDispatcher) {
            runCatching {
                validateUsernameUseCase.execute(ValidateUsernameUseCase.Params(it))
                emit(Validation.Valid(it))
            }.getOrElse { e ->
                emit(Validation.Invalid(it, e))
            }
        }
    }

    val password = MutableLiveData<String>()

    val passwordValidation: LiveData<Validation<String>> = password.switchMap {
        liveData(defaultDispatcher) {
            runCatching {
                validatePasswordUseCase.execute(ValidatePasswordUseCase.Params(it))
                emit(Validation.Valid(it))
            }.getOrElse { e ->
                emit(Validation.Invalid(it, e))
            }
        }
    }

    val enableLogin = MediatorLiveData<Boolean>().apply {
        addSource(username) {
            value = !username.value.isNullOrEmpty() && !password.value.isNullOrEmpty()
        }
        addSource(password) {
            value = !username.value.isNullOrEmpty() && !password.value.isNullOrEmpty()
        }
    }

    private val _rememberLogin = liveData(defaultDispatcher) {
        checkLoginUseCase.execute().apply {
            emit(this)
            if (this) {
                _loginResult.postValue(Event(Result.Success(true)))
            }
        }
    }
    val rememberLogin: MutableLiveData<Boolean> =
        _rememberLogin.map { it } as MutableLiveData<Boolean>

    fun login() {
        viewModelScope.launch(defaultDispatcher) {
            _loginResult.postValue(Event(Result.Loading))

            runCatching {
                loginUseCase.execute(
                    LoginUseCase.Params(
                        username.value ?: "",
                        password.value ?: "",
                        rememberLogin.value ?: false
                    )
                )
                _loginResult.postValue(Event(Result.Success(true)))
            }.getOrElse { e ->
                e.mapToCleanException().apply {
                    _loginResult.postValue(Event(Result.Error(this)))
                    setExceptionAsync(this)
                }
            }
        }
    }
}
