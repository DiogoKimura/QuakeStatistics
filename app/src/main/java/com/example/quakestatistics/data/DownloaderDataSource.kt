package com.example.quakestatistics.data

import androidx.fragment.app.Fragment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class DownloaderDataSource {

    private lateinit var fragment : Fragment

    fun downloadQuakeLogFile(callback: DownloaderCallback, fragment: Fragment){
        this.fragment = fragment

        HTTPClient.retrofit()
            .create(DownloaderAPI::class.java)
            .downloadQuakeLogFile()
            .enqueue(object: Callback<ResponseBody>{
                override fun onResponse(
                    call: Call<ResponseBody>?,
                    response: Response<ResponseBody>?
                ) {
                    response?.body()?.let { writeFile(it) }
                    callback.onSuccess(readfile())
                }

                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    TODO("Not yet implemented")
                }
            })
    }

    private fun writeFile(responseBody: ResponseBody?) {
        val path = fragment.context?.filesDir
        val file = File(path, "qgames.log")

        responseBody?.bytes()?.let {
            if (file.exists())return
            file.appendBytes(it)
        }
    }

    private fun readfile() : List<String> {
        val path = fragment.context?.filesDir
        val file = File(path, "qgames.log")

        return file.readLines()
    }

}