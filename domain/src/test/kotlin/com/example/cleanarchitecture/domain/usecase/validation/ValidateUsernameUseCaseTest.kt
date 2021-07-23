package com.example.cleanarchitecture.domain.usecase.validation

import com.example.cleanarchitecture.domain.base.BaseTest
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ValidateUsernameUseCaseTest : BaseTest() {

    private lateinit var validateUsernameUseCase: ValidateUsernameUseCase

    @Before
    fun setup() {
        validateUsernameUseCase = ValidateUsernameUseCase()
    }

    @Test
    fun `username is valid`() {

        runBlockingTest {
            // given
            val param = ValidateUsernameUseCase.Params("anhanh")

            // when
            val result = validateUsernameUseCase.execute(param)

            // then
            Assert.assertEquals(true, result)
        }
    }

    @Test
    fun `username is short`() {

        runBlockingTest {
            var exception: Throwable? = null

            // given
            val param = ValidateUsernameUseCase.Params("anh")

            // when
            runCatching {
                validateUsernameUseCase.execute(param)
                true
            }.getOrElse {
                exception = it
                false
            }

            // then
            Assert.assertEquals(CleanExceptionType.INPUT_SHORT, (exception as CleanException).type)
        }
    }

    @Test
    fun `username is null or empty`() {

        runBlockingTest {
            var exception: Throwable? = null

            // given
            val param = ValidateUsernameUseCase.Params("")

            // when
            runCatching {
                validateUsernameUseCase.execute(param)
                true
            }.getOrElse {
                exception = it
                false
            }

            // then
            Assert.assertEquals(
                CleanExceptionType.INPUT_NULL_OR_EMPTY,
                (exception as CleanException).type
            )
        }
    }
}
