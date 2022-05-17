package com.example.quakestatistics.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class KillMode(
    val name : KillModeEnum,
    var quantity : Int = 1
) : Parcelable {
    fun add() = quantity++
}