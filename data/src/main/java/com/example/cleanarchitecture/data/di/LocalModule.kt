package com.example.cleanarchitecture.data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.cleanarchitecture.data.pref.AppPref
import com.example.cleanarchitecture.data.pref.PrefHelper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providePrefHelper(appPref: AppPref): PrefHelper {
        return appPref
    }

    @Provides
    @Singleton
    fun provideAppPref(sharedPreferences: SharedPreferences, gson: Gson): AppPref {
        return AppPref(sharedPreferences, gson)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }
}
