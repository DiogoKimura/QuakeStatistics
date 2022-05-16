package com.example.quakestatistics.presenter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quakestatistics.R
import com.example.quakestatistics.model.MatchItem
import com.example.quakestatistics.view.MatchListFragment

class MatchListAdapter(
    private val fragment: MatchListFragment,
    private val matchItemList: List<MatchItem>
) : RecyclerView.Adapter<MatchListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.match_item, parent, false)
        return MatchListViewHolder(view, fragment)
    }

    override fun onBindViewHolder(holder: MatchListViewHolder, position: Int) {
        val item = matchItemList[position]

        holder.index.text = position.toString()
        holder.title.text = fragment.context?.getString(R.string.server_label, item.title)
        holder.body1.text = fragment.context?.getString(R.string.player_label, item.body1)
        holder.body2.text = fragment.context?.getString(R.string.kills_label, item.body2)
    }

    override fun getItemCount(): Int {
        return matchItemList.size
    }
}