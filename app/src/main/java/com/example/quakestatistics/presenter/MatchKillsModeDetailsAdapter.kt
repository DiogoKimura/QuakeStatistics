package com.example.quakestatistics.presenter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quakestatistics.R
import com.example.quakestatistics.model.KillMode
import com.example.quakestatistics.model.MatchItem
import com.example.quakestatistics.model.UserStats
import com.example.quakestatistics.model.UsersStats
import com.example.quakestatistics.view.MatchDetailsKillsModeFragment
import com.example.quakestatistics.view.MatchDetailsPlayerFragment
import com.example.quakestatistics.view.MatchListFragment

class MatchKillsModeDetailsAdapter(
    private val fragment: MatchDetailsKillsModeFragment,
    private val killsMode: List<KillMode>
) : RecyclerView.Adapter<MatchKillsModeDetailsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchKillsModeDetailsHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.kills_mode_item, parent, false)
        return MatchKillsModeDetailsHolder(view)
    }

    override fun onBindViewHolder(holder: MatchKillsModeDetailsHolder, position: Int) {
        val item = killsMode[position]

        holder.killModeName.text = item.name.name
        holder.killModeQuantity.text = item.quantity.toString()
    }

    override fun getItemCount(): Int {
        return killsMode.size
    }
}