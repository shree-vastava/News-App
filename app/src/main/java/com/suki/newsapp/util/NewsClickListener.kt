package com.suki.newsapp.util

import android.widget.ImageView
import android.widget.TextView
import com.suki.newsapp.entities.Doc

/**
 * Returning the views along with the
 * data class to achieve the Shared
 * Element transition when switching activity
 */
interface NewsClickListener {
    fun onNewsClicked(doc: Doc, tv_title: TextView, image: ImageView)
}