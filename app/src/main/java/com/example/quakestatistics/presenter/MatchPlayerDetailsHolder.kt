package com.example.quakestatistics.presenter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quakestatistics.R

class MatchPlayerDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val playerName: TextView = itemView.findViewById(R.id.player_name)
    val playerScore: TextView = itemView.findViewById(R.id.player_score)
    val playerStats: TextView = itemView.findViewById(R.id.player_stats)
}