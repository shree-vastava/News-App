package com.suki.newsapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.suki.newsapp.R
import com.suki.newsapp.entities.Multimedia

@BindingAdapter("load_image")
fun loadImage(view: ImageView, multimedia: List<Multimedia>) {
    if(multimedia.isNotEmpty()){
        Glide.with(view.context)
            .load("https://static01.nyt.com/${multimedia[0].url}")
            .into(view)
    }
    else{
        view.setImageResource(R.drawable.not_found)
    }
}