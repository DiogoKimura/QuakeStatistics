package com.example.quakestatistics.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchItem(
    val title: String,
    val body1: String,
    val body2: String,
    var isCorrupted: Boolean = false,
    val usersStats: UsersStats? = null
) : Parcelable