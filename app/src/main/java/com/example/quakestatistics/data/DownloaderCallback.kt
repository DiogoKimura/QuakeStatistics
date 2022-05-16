package com.example.quakestatistics.data

import okhttp3.ResponseBody

interface DownloaderCallback {
    fun onSuccess(fileLines: List<String>)

    fun onError()

    fun onComplete()
}