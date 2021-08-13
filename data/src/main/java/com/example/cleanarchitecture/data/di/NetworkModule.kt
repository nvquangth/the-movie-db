package com.example.cleanarchitecture.data.di

import com.example.cleanarchitecture.data.BuildConfig
import com.example.cleanarchitecture.data.anotation.APIKeyInterceptorOkHttpClient
import com.example.cleanarchitecture.data.anotation.AuthOkHttpClient
import com.example.cleanarchitecture.data.anotation.NoInterceptorOkHttpClient
import com.example.cleanarchitecture.data.interceptor.AuthInterceptor
import com.example.cleanarchitecture.data.network.RetrofitBuilder
import com.example.cleanarchitecture.data.network.api.AuthApi
import com.example.cleanarchitecture.data.network.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    @AuthOkHttpClient
    fun provideAuthRetrofit(retrofitBuilder: RetrofitBuilder): Retrofit = retrofitBuilder
        .setBaseUrl(BuildConfig.BASE_URL_AUTH)
        .build()

    @Singleton
    @Provides
    @NoInterceptorOkHttpClient
    fun provideRetrofit(retrofitBuilder: RetrofitBuilder): Retrofit = retrofitBuilder.build()

    @Singleton
    @Provides
    @APIKeyInterceptorOkHttpClient
    fun provideAPIKeyRetrofit(
        retrofitBuilder: RetrofitBuilder,
        authInterceptor: AuthInterceptor
    ): Retrofit = retrofitBuilder
        .addInterceptors(authInterceptor)
        .setBaseUrl(BuildConfig.BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideMovieApi(@APIKeyInterceptorOkHttpClient retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideAuthApi(@AuthOkHttpClient retrofit: Retrofit): AuthApi =
        retrofit.create(AuthApi::class.java)
}
