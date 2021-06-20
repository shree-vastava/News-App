package com.suki.newsapp.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Headline(
    val content_kicker: String?,
    val kicker: String?,
    val main: String?,
    val name: String?,
    val print_headline: String?,
    val seo: String?,
    val sub: String?
): Parcelable