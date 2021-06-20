package com.suki.newsapp.entities

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.suki.newsapp.R
import kotlinx.android.parcel.Parceler
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_grid_item.view.*
import java.text.SimpleDateFormat


@Parcelize
data class Doc(
    val _id: String,
    val abstract: String,
    val document_type: String,
    val headline: Headline,
    val keywords: List<Keyword>,
    val lead_paragraph: String,
    val multimedia: List<Multimedia>,
    val news_desk: String,
    val print_page: String?,
    val print_section: String?,
    val pub_date: String,
    val section_name: String,
    val snippet: String,
    val source: String,
    val type_of_material: String,
    val uri: String,
    val web_url: String,
    val word_count: Int,
    var display_date: String
): Parcelable

