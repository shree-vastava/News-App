package com.suki.newsapp.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Multimedia(
    val caption: String?,
    val credit: String?,
    val crop_name: String?,
    val height: Int,
    val rank: Int,
    val subType: String?,
    val subtype: String?,
    val type: String?,
    val url: String?,
    val width: Int
): Parcelable