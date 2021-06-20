package com.suki.newsapp.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Byline(
    val organization: String,
    val original: String,
    val person: List<Person>
): Parcelable