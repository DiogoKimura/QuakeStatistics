package com.example.quakestatistics.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.quakestatistics.model.MatchItem
import com.example.quakestatistics.view.MatchDetailsKillsModeFragment
import com.example.quakestatistics.view.MatchDetailsPlayerFragment

class MatchDetailViewPagerAdapter(
    activity: FragmentActivity,
    val itemsCount: Int,
    val matchItem: MatchItem
) : FragmentStateAdapter(activity) {

    override fun getItemCount() = itemsCount

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MatchDetailsPlayerFragment.getInstance(matchItem.usersStats!!)
            else -> MatchDetailsKillsModeFragment.getInstance()
        }
    }
}