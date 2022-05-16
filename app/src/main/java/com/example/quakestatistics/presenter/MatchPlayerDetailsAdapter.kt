package com.example.quakestatistics.presenter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quakestatistics.R
import com.example.quakestatistics.model.MatchItem
import com.example.quakestatistics.model.UserStats
import com.example.quakestatistics.model.UsersStats
import com.example.quakestatistics.view.MatchDetailsPlayerFragment
import com.example.quakestatistics.view.MatchListFragment

class MatchPlayerDetailsAdapter(
    private val fragment: MatchDetailsPlayerFragment,
    private val usersStats: ArrayList<UserStats>
) : RecyclerView.Adapter<MatchPlayerDetailsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPlayerDetailsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_item, parent, false)
        return MatchPlayerDetailsHolder(view)
    }

    override fun onBindViewHolder(holder: MatchPlayerDetailsHolder, position: Int) {
        val item = usersStats[position]

        holder.playerName.text = item.name
        holder.playerScore.text = fragment.context?.getString(R.string.score_label, item.getPoints().toString())
        holder.playerStats.text = fragment.context?.getString(R.string.stats_label,
            item.kills, item.deaths, item.suicide)
    }

    override fun getItemCount(): Int {
        return usersStats.size
    }
}