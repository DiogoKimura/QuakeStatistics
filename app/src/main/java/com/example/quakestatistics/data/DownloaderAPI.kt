package com.example.quakestatistics.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming

interface DownloaderAPI {

    @Streaming
    @GET("/DiogoKimura/RawQuakeLogParser/main/qgames.log")
    fun downloadQuakeLogFile() : Call<ResponseBody>
}