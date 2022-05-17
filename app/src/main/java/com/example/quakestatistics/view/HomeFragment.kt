package com.example.quakestatistics.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.Group
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quakestatistics.R
import com.example.quakestatistics.presenter.HomePresenter


class HomeFragment : Fragment() {

    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = HomePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.downloadFile()
    }

    fun callMatchList(fileLines: List<String>) {
        val bundle = bundleOf("logLines" to fileLines)
        findNavController().navigate(R.id.action_nav_home_to_nav_view_matches, bundle)
    }

    fun onDownloadError() {
        val loadingGroup = view?.findViewById<Group>(R.id.loading_group)
        loadingGroup?.visibility = View.GONE

        val errorGroup = view?.findViewById<Group>(R.id.error_group)
        errorGroup?.visibility = View.VISIBLE
        val buttonRetry = view?.findViewById<Button>(R.id.btn_retry)
        buttonRetry?.setOnClickListener {
            loadingGroup?.visibility = View.VISIBLE
            errorGroup?.visibility = View.GONE
            presenter.downloadFile()
        }
    }
}