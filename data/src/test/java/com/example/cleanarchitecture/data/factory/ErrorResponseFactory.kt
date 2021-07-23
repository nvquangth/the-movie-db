package com.example.cleanarchitecture.data.factory

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import java.net.HttpURLConnection

object ErrorResponseFactory {

    val USERNAME_OR_PASSWORD_INCORRECT: Response<ResponseBody>
        get() {

            val errorBody =
                """
                {
                    "message": "Username or password is incorrect."
                }
                """

            return Response.error(
                HttpURLConnection.HTTP_UNAUTHORIZED,
                errorBody.toResponseBody("application/json".toMediaTypeOrNull())
            )
        }

    val SERVER_MAINTENANCE: Response<ResponseBody>
        get() {

            val errorBody =
                """
                    {
                        "type": "ServerMaintenanceException",
                        "message": "Server maintenance, please try again later."
                    }
                """

            return Response.error(
                HttpURLConnection.HTTP_UNAVAILABLE,
                errorBody.toResponseBody("application/json".toMediaTypeOrNull())
            )
        }

    val APP_FORCE_UPDATE: Response<ResponseBody>
        get() {

            val errorBody =
                """
                    {
                        "type": "ForceUpdateAppException",
                        "message": "Old app version isn't support, please update app to new version."
                    }
                """

            return Response.error(
                HttpURLConnection.HTTP_UNAVAILABLE,
                errorBody.toResponseBody("application/json".toMediaTypeOrNull())
            )
        }

    val UNKNOWN: Response<ResponseBody>
        get() {

            val errorBody =
                """
                   {
                        "message": "Oops, something went wrong."
                   }
                """
            return Response.error(
                123321,
                errorBody.toResponseBody("application/json".toMediaTypeOrNull())
            )
        }
}
