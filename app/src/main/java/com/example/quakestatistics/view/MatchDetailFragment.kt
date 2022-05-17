package com.example.quakestatistics.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.quakestatistics.R
import com.example.quakestatistics.model.MatchItem
import com.example.quakestatistics.presenter.MatchDetailViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MatchDetailFragment : Fragment() {

    private lateinit var matchItem: MatchItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        matchItem = arguments?.get("match") as MatchItem
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.header_title).text = getString(R.string.server_label, matchItem.title)
        view.findViewById<TextView>(R.id.header_player).text = getString(R.string.player_label, matchItem.body1)
        view.findViewById<TextView>(R.id.header_kills).text = getString(R.string.kills_label, matchItem.body2)
        view.findViewById<TextView>(R.id.header_best_player).text = getString(R.string.best_player_label, matchItem.bestPlayerName())

        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = view.findViewById<ViewPager2>(R.id.view_pager)
        val tabNames = listOf("Players", "Kills by Mode")

        viewPager.adapter = MatchDetailViewPagerAdapter(activity!!, tabNames.size, matchItem)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
}