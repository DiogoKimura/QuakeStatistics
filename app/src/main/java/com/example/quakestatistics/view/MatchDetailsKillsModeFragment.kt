package com.example.quakestatistics.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quakestatistics.R

class MatchDetailsKillsModeFragment : Fragment() {

    companion object {
        fun getInstance()= MatchDetailsKillsModeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match_details_kills_mode, container, false)
    }
}