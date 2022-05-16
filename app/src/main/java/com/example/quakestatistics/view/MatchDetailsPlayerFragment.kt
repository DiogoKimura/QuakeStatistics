package com.example.quakestatistics.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quakestatistics.R
import com.example.quakestatistics.model.UsersStats
import com.example.quakestatistics.parser.LogParser
import com.example.quakestatistics.presenter.MatchListAdapter
import com.example.quakestatistics.presenter.MatchPlayerDetailsAdapter

class MatchDetailsPlayerFragment : Fragment() {

    companion object {
        fun getInstance(usersStats: UsersStats) : Fragment {
            val matchDetailsPlayerFragment = MatchDetailsPlayerFragment()
            val bundle = Bundle()
            bundle.putParcelable("usersStats", usersStats)
            matchDetailsPlayerFragment.arguments = bundle
            return matchDetailsPlayerFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_details_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usersStats : UsersStats = requireArguments().getParcelable("usersStats")!!

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_match_list)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = MatchPlayerDetailsAdapter(this, usersStats.getUserList())
    }
}