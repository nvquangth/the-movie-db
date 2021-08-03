package com.example.cleanarchitecture.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// import kotlinx.android.parcel.Parcelize

@Parcelize
data class ThemeItem(
    val color: String,
    val name: String
) : Parcelable
