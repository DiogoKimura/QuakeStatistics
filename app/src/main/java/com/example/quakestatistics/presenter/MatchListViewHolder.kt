package com.example.quakestatistics.presenter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quakestatistics.R

class MatchListViewHolder(
    itemView: View,
    onItemClickListener: OnItemClickListener
) : RecyclerView.ViewHolder(itemView) {
    val index: TextView = itemView.findViewById(R.id.player_name)
    val title: TextView = itemView.findViewById(R.id.player_score)
    val body1: TextView = itemView.findViewById(R.id.match_item_body_1)
    val body2: TextView = itemView.findViewById(R.id.player_stats)

    init {
        itemView.setOnClickListener {
            onItemClickListener.onClick(bindingAdapterPosition)
        }
    }
}