package com.example.cleanarchitecture.domain.usecase.auth

import com.example.cleanarchitecture.domain.base.BaseTest
import com.example.cleanarchitecture.domain.extension.mock
import com.example.cleanarchitecture.domain.extension.whenever
import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginUseCaseTest : BaseTest() {

    private val authRepository = mock<AuthRepository>()
    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        loginUseCase = LoginUseCase(authRepository)
    }

    @Test
    fun `login success`() {

        runBlockingTest {
            // given
            val params = LoginUseCase.Params("admin", "123456")
            whenever(authRepository.login(params.username, params.password)).thenReturn(true)

            // when
            val result = loginUseCase.execute(params)

            // then
            Assert.assertEquals(true, result)
        }
    }

    @Test
    fun `login fail`() {

        var exception: Throwable? = null

        runBlockingTest {
            // given
            val params = LoginUseCase.Params("anhanh", "123456")
            whenever(authRepository.login(params.username, params.password)).thenAnswer {
                throw CleanException(CleanExceptionType.UNAUTHORIZED)
            }

            // when
            runCatching {
                loginUseCase.execute(params)
                true
            }.getOrElse {
                exception = it
                false
            }

            // then
            Assert.assertEquals(CleanExceptionType.UNAUTHORIZED, (exception as CleanException).type)
        }
    }
}
