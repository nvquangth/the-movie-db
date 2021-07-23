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
class ValidatePasswordUseCaseTest : BaseTest() {

    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun setup() {
        validatePasswordUseCase = ValidatePasswordUseCase()
    }

    @Test
    fun `password is valid`() {

        runBlockingTest {
            // given
            val param = ValidatePasswordUseCase.Params("123456789")

            // when
            val result = validatePasswordUseCase.execute(param)

            // then
            Assert.assertEquals(true, result)
        }
    }

    @Test
    fun `password is short`() {

        var exception: Throwable? = null
        runBlockingTest {

            // given
            val param = ValidatePasswordUseCase.Params("1")

            // when
            runCatching {
                validatePasswordUseCase.execute(param)
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
    fun `password is null or empty`() {

        var exception: Throwable? = null
        runBlockingTest {

            // given
            val param = ValidatePasswordUseCase.Params(null)

            // when
            runCatching {
                validatePasswordUseCase.execute(param)
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
