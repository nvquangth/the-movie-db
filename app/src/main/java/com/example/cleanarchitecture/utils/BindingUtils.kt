package com.example.cleanarchitecture.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.cleanarchitecture.R

@BindingAdapter("glideUrl")
fun ImageView.setGlideUrl(url: String?) {
    val baseUrl = "https://image.tmdb.org/t/p/w185"
    GlideApp.with(context)
        .load("${baseUrl}$url")
        .placeholder(R.drawable.image_default)
        .into(this)
}
