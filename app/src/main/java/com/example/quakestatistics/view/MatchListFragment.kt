package com.example.quakestatistics.view

import android.os.Bundle
import android.service.autofill.FieldClassification
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quakestatistics.presenter.MatchListAdapter
import com.example.quakestatistics.R
import com.example.quakestatistics.model.MatchItem
import com.example.quakestatistics.parser.LogParser
import com.example.quakestatistics.presenter.OnItemClickListener

class MatchListFragment : Fragment(), OnItemClickListener {

    private lateinit var matchList : ArrayList<MatchItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getStringArrayList("logLines")?.let {
            matchList = LogParser.getInstance(it).getMatchList() }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_match_list)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = MatchListAdapter(this, matchList)
    }

    override fun onClick(position: Int) {
        val bundle = bundleOf("match" to matchList[position])
        findNavController().navigate(R.id.action_nav_match_list_to_nav_match_details, bundle)
    }
}

