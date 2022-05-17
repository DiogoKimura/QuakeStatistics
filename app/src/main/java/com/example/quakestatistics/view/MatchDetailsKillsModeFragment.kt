package com.example.quakestatistics.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quakestatistics.R
import com.example.quakestatistics.model.KillMode
import com.example.quakestatistics.model.UserStats
import com.example.quakestatistics.presenter.MatchKillsModeDetailsAdapter
import com.example.quakestatistics.presenter.MatchPlayerDetailsAdapter

class MatchDetailsKillsModeFragment : Fragment() {

    companion object {
        fun getInstance(killsMode: ArrayList<KillMode>) : Fragment {
            val matchDetailsKillsModeFragment = MatchDetailsKillsModeFragment()
            val bundle = Bundle()
            bundle.putSerializable("killsMode", killsMode)
            matchDetailsKillsModeFragment.arguments = bundle
            return matchDetailsKillsModeFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_details_kills_mode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val killModeList = requireArguments().getParcelableArrayList<KillMode>("killsMode")!!

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_kill_mode)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = MatchKillsModeDetailsAdapter(this, killModeList)
    }
}