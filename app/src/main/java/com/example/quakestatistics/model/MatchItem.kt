package com.example.quakestatistics.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchItem(
    val title: String,
    val body1: String,
    val body2: String,
    var isCorrupted: Boolean = false,
    val userList: List<UserStats>,
    val killsMode: List<KillMode>
) : Parcelable {

    fun bestPlayerName() : String {
        var bestPlayerName = ""
        var bestPlayerScore = Int.MIN_VALUE
        userList.forEach {
            if (it.getScore() > bestPlayerScore) {
                bestPlayerName = it.name
                bestPlayerScore = it.getScore()
            }
        }
        return bestPlayerName
    }
}