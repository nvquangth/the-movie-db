package com.example.cleanarchitecture.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.cleanarchitecture.data.base.BaseTest
import com.example.cleanarchitecture.data.extension.mock
import com.example.cleanarchitecture.data.extension.whenever
import com.example.cleanarchitecture.data.factory.AuthFactory
import com.example.cleanarchitecture.data.factory.ErrorResponseFactory
import com.example.cleanarchitecture.data.pref.AppPref
import com.example.cleanarchitecture.data.pref.PrefHelper
import com.example.cleanarchitecture.data.remote.AuthApi
import com.example.cleanarchitecture.domain.repository.AuthRepository
import com.example.cleanarchitecture.entity.exception.CleanException
import com.example.cleanarchitecture.entity.exception.CleanExceptionType
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import retrofit2.HttpException
import java.net.UnknownHostException

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
@ExperimentalCoroutinesApi
class AuthRepositoryImplTest : BaseTest() {

    private val authApi = mock<AuthApi>()

    private lateinit var context: Context
    private lateinit var gson: Gson
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var prefHelper: PrefHelper
    private lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        gson = Gson()
        context = RuntimeEnvironment.systemContext
        sharedPreferences = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
        prefHelper = AppPref(sharedPreferences, gson)
        authRepository = AuthRepositoryImpl(authApi, prefHelper)
    }

    @Test
    fun `login success test`() {

        val authEntity = AuthFactory.AUTH_ENTITY

        runBlockingTest {
            val username = "admin"
            val password = "1"

            // given
            whenever(authApi.login(username, password)).thenReturn(authEntity)

            // when
            val result = authRepository.login(username, password)

            // then
            Assert.assertEquals(true, result)
            Assert.assertEquals(authEntity, prefHelper.getAuth())
        }
    }

    @Test
    fun `unauthorized test`() {

        val errorResponse = ErrorResponseFactory.USERNAME_OR_PASSWORD_INCORRECT
        var e: Throwable? = null

        runBlockingTest {
            val username = "admin"
            val password = "123"

            // given
            whenever(authApi.login(username, password)).thenAnswer {
                throw HttpException(errorResponse)
            }

            // when
            val result = runCatching {
                authRepository.login(username, password)
            }.getOrElse {
                e = it
                false
            }

            // then
            Assert.assertEquals(false, result)
            Assert.assertEquals(CleanExceptionType.UNAUTHORIZED, (e as? CleanException)?.type)
        }
    }

    @Test
    fun `server maintenance test`() {

        val errorResponse = ErrorResponseFactory.SERVER_MAINTENANCE
        var e: Throwable? = null

        runBlockingTest {
            val username = "admin"
            val password = "123"

            // given
            whenever(authApi.login(username, password)).thenAnswer {
                throw HttpException(errorResponse)
            }

            // when
            val result = runCatching {
                authRepository.login(username, password)
            }.getOrElse {
                e = it
                false
            }

            // then
            Assert.assertEquals(false, result)
            Assert.assertEquals(CleanExceptionType.SERVER_MAINTENANCE, (e as? CleanException)?.type)
        }
    }

    @Test
    fun `force update app test`() {

        val errorResponse = ErrorResponseFactory.APP_FORCE_UPDATE
        var e: Throwable? = null

        runBlockingTest {
            val username = "admin"
            val password = "123"

            // given
            whenever(authApi.login(username, password)).thenAnswer {
                throw HttpException(errorResponse)
            }

            // when
            val result = runCatching {
                authRepository.login(username, password)
            }.getOrElse {
                e = it
                false
            }

            // then
            Assert.assertEquals(false, result)
            Assert.assertEquals(CleanExceptionType.APP_FORCE_UPDATE, (e as? CleanException)?.type)
        }
    }

    @Test
    fun `unknown host test`() {
        var e: Throwable? = null

        runBlockingTest {
            val username = "admin"
            val password = "123"

            // given
            whenever(authApi.login(username, password)).thenAnswer {
                throw UnknownHostException()
            }

            // when
            val result = runCatching {
                authRepository.login(username, password)
            }.getOrElse {
                e = it
                false
            }

            // then
            Assert.assertEquals(false, result)
            Assert.assertEquals(
                CleanExceptionType.NETWORK_NO_CONNECTION,
                (e as? CleanException)?.type
            )
        }
    }
}
