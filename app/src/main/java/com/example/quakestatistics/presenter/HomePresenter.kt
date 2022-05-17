package com.example.quakestatistics.presenter

import com.example.quakestatistics.data.DownloaderCallback
import com.example.quakestatistics.data.DownloaderDataSource
import com.example.quakestatistics.view.HomeFragment
import okhttp3.ResponseBody

class HomePresenter(
    private val fragment: HomeFragment,
    private val dataSource: DownloaderDataSource = DownloaderDataSource()
) : DownloaderCallback {

    fun downloadFile(){
        dataSource.downloadQuakeLogFile(this, fragment)
    }

    override fun onSuccess(fileLines: List<String>) {
        fragment.callMatchList(fileLines)
    }

    override fun onError() {
        fragment.onDownloadError()
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }

}