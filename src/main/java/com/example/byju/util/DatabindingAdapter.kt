package com.example.byju.util

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import androidx.databinding.BindingAdapter


@BindingAdapter("newsImage")
fun loadImage(view: ImageView, imageUrl: String) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions().centerCrop())
        .into(view)
}