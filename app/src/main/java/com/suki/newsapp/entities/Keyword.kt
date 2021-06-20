package com.suki.newsapp.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Keyword(
    val major: String,
    val name: String,
    val rank: Int,
    val value: String
): Parcelable