package com.example.cleanarchitecture.ui.login

import com.bt.presentation.base.model.Validation
import com.example.cleanarchitecture.base.BaseViewModelTest
import com.example.cleanarchitecture.domain.usecase.auth.LoginUseCase
import com.example.cleanarchitecture.domain.usecase.validation.ValidatePasswordUseCase
import com.example.cleanarchitecture.domain.usecase.validation.ValidateUsernameUseCase
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import com.example.cleanarchitecture.extension.getOrAwaitValue
import com.example.cleanarchitecture.extension.mock
import com.example.cleanarchitecture.extension.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: LoginViewModel

    private val validateUsernameUseCase = mock<ValidateUsernameUseCase>()
    private val validatePasswordUseCase = mock<ValidatePasswordUseCase>()
    private val loginUseCase = mock<LoginUseCase>()

    override fun setup() {
        super.setup()
        viewModel = LoginViewModel(
            validateUsernameUseCase = validateUsernameUseCase,
            validatePasswordUseCase = validatePasswordUseCase,
            loginUseCase = loginUseCase,
            defaultDispatcher = testDispatcher
        )
    }

    @Test
    fun `login success`() {
        testCoroutineRule.runBlockingTest {
            viewModel.username.value = "admin"
            viewModel.password.value = "123456"
            whenever(loginUseCase.execute(LoginUseCase.Params("admin", "123456"))).thenReturn(true)

            // when
            viewModel.login()

            // then
            Assert.assertEquals(true, viewModel.loginResult.value?.peekContent()?.data)
        }
    }

    // Login fail
    @Test
    fun `login fail`() {

        testCoroutineRule.runBlockingTest {
            // given
            viewModel.username.value = "anhanh"
            viewModel.password.value = "123456"
            whenever(loginUseCase.execute(LoginUseCase.Params("anhanh", "123456"))).thenAnswer {
                throw CleanException(CleanExceptionType.UNAUTHORIZED)
            }

            // when
            viewModel.login()
        }

        // then
        val e = viewModel.loginResult.value?.peekContent()?.error
        Assert.assertEquals(true, e is CleanException)
        Assert.assertEquals(CleanExceptionType.UNAUTHORIZED, (e as CleanException).type)
    }

    // server maintenance
    @Test
    fun `server maintenance`() {

        testCoroutineRule.runBlockingTest {
            // given
            viewModel.username.value = "admin1"
            viewModel.password.value = "123456"
            whenever(loginUseCase.execute(LoginUseCase.Params("admin1", "123456"))).thenAnswer {
                throw CleanException(CleanExceptionType.SERVER_MAINTENANCE)
            }

            // when
            viewModel.login()
        }

        // then
        val e = viewModel.loginResult.value?.peekContent()?.error
        Assert.assertEquals(true, e is CleanException)
        Assert.assertEquals(CleanExceptionType.SERVER_MAINTENANCE, (e as CleanException).type)
    }

    // no internet connection
    @Test
    fun `no internet connection`() {

        testCoroutineRule.runBlockingTest {
            // given
            viewModel.username.value = "admin2"
            viewModel.password.value = "123456"
            whenever(loginUseCase.execute(LoginUseCase.Params("admin2", "123456"))).thenAnswer {
                throw CleanException(CleanExceptionType.NETWORK_NO_CONNECTION)
            }

            // when
            viewModel.login()
        }

        // then
        val e = viewModel.loginResult.value?.peekContent()?.error
        Assert.assertEquals(true, e is CleanException)
        Assert.assertEquals(CleanExceptionType.NETWORK_NO_CONNECTION, (e as CleanException).type)
    }

    // connection time out
    @Test
    fun `connection timeout`() {

        testCoroutineRule.runBlockingTest {
            // given
            viewModel.username.value = "admin3"
            viewModel.password.value = "123456"
            whenever(loginUseCase.execute(LoginUseCase.Params("admin3", "123456"))).thenAnswer {
                throw CleanException(CleanExceptionType.NETWORK_TIMEOUT)
            }

            // when
            viewModel.login()
        }

        // then
        val e = viewModel.loginResult.value?.peekContent()?.error
        Assert.assertEquals(true, e is CleanException)
        Assert.assertEquals(CleanExceptionType.NETWORK_TIMEOUT, (e as CleanException).type)
    }

    // force update app
    @Test
    fun `force update app`() {

        testCoroutineRule.runBlockingTest {
            // given
            viewModel.username.value = "admin4"
            viewModel.password.value = "123456"
            whenever(loginUseCase.execute(LoginUseCase.Params("admin4", "123456"))).thenAnswer {
                throw CleanException(CleanExceptionType.APP_FORCE_UPDATE)
            }

            // when
            viewModel.login()
        }

        // then
        val e = viewModel.loginResult.value?.peekContent()?.error
        Assert.assertEquals(true, e is CleanException)
        Assert.assertEquals(CleanExceptionType.APP_FORCE_UPDATE, (e as CleanException).type)
    }

    @Test
    fun `username is valid`() {

        testCoroutineRule.runBlockingTest {
            // given
            whenever(validateUsernameUseCase.execute(ValidateUsernameUseCase.Params("admin"))).thenReturn(
                true
            )

            // when
            viewModel.username.value = "admin"

            // then
            Assert.assertEquals(
                true,
                viewModel.usernameValidation.getOrAwaitValue() is Validation.Valid
            )
        }
    }

    @Test
    fun `username is empty`() {
        testCoroutineRule.runBlockingTest {
            // given
            whenever(validateUsernameUseCase.execute(ValidateUsernameUseCase.Params(""))).thenAnswer {
                throw CleanException(CleanExceptionType.INPUT_EMPTY)
            }

            // when
            viewModel.username.value = ""

            // then
            Assert.assertEquals(
                true,
                viewModel.usernameValidation.getOrAwaitValue() is Validation.Invalid
            )
        }
    }

    @Test
    fun `username is short`() {
        testCoroutineRule.runBlockingTest {
            // given
            whenever(validateUsernameUseCase.execute(ValidateUsernameUseCase.Params("ad"))).thenAnswer {
                throw CleanException(CleanExceptionType.INPUT_SHORT, "Username invalid")
            }

            // when
            viewModel.username.value = "ad"

            // then
            Assert.assertEquals(
                true,
                viewModel.usernameValidation.getOrAwaitValue() is Validation.Invalid
            )
        }
    }

    @Test
    fun `password is valid`() {

        testCoroutineRule.runBlockingTest {
            // given
            whenever(validatePasswordUseCase.execute(ValidatePasswordUseCase.Params("123456"))).thenReturn(
                true
            )

            // when
            viewModel.password.value = "123456"

            // then
            Assert.assertEquals(
                true,
                viewModel.passwordValidation.getOrAwaitValue() is Validation.Valid
            )
        }
    }

    @Test
    fun `password is empty`() {

        testCoroutineRule.runBlockingTest {
            // given
            whenever(validatePasswordUseCase.execute(ValidatePasswordUseCase.Params(""))).thenAnswer {
                throw CleanException(CleanExceptionType.INPUT_EMPTY)
            }

            // when
            viewModel.password.value = ""

            // then
            Assert.assertEquals(
                true,
                viewModel.passwordValidation.getOrAwaitValue() is Validation.Invalid
            )
        }
    }

    @Test
    fun `password is short`() {

        testCoroutineRule.runBlockingTest {
            // given
            whenever(validatePasswordUseCase.execute(ValidatePasswordUseCase.Params(""))).thenAnswer {
                throw CleanException(CleanExceptionType.INPUT_SHORT, "Password invalid")
            }

            // when
            viewModel.password.value = ""

            // then
            Assert.assertEquals(
                true,
                viewModel.passwordValidation.getOrAwaitValue() is Validation.Invalid
            )
        }
    }

    @Test
    fun `login button is enable`() {
        // given

        // when
        viewModel.username.value = "admin"
        viewModel.password.value = "123"

        // then
        Assert.assertEquals(true, viewModel.enableLogin.getOrAwaitValue())
    }

    @Test
    fun `login button is disable if username invalid`() {
        // given

        // when
        viewModel.username.value = ""
        viewModel.password.value = "123"

        // then
        Assert.assertEquals(false, viewModel.enableLogin.getOrAwaitValue())
    }

    @Test
    fun `login button is disable if password invalid`() {
        // given

        // when
        viewModel.username.value = "admin"
        viewModel.password.value = ""

        // then
        Assert.assertEquals(false, viewModel.enableLogin.getOrAwaitValue())
    }
}
