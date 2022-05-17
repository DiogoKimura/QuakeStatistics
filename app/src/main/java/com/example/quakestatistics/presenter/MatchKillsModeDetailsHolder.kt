package com.example.quakestatistics.presenter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quakestatistics.R

class MatchKillsModeDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val killModeName: TextView = itemView.findViewById(R.id.kill_mode_name)
    val killModeQuantity: TextView = itemView.findViewById(R.id.kill_mode_quantity)
}