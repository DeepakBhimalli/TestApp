package com.comcast.test.testapp.restapi

import android.os.AsyncTask
import android.util.Log
import com.comcast.test.testapp.model.RelatedTopicsInfo
import com.comcast.test.testapp.model.SimpsonsCharacterResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.*

class RestApiRequest(private val listener: GetDataListener?) {
    private val TAG = RestApiRequest::class.java.simpleName

    interface GetDataListener {
        fun getDataResponse(response: ArrayList<RelatedTopicsInfo>?)
    }

    /**
     * Method that invokes task for fetch the data from web
     */
    fun requestCharactersData(url: String) {
        val task = GetCharactersNameTask()
        task.execute(url)
    }

    private inner class GetCharactersNameTask : AsyncTask<String, Void, SimpsonsCharacterResponse>() {

        override fun doInBackground(vararg params: String): SimpsonsCharacterResponse? {
            val client = OkHttpClient()

            val request = Request.Builder().url(params[0]).build()
            val response: Response
            try {
                response = client.newCall(request).execute()

                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()

                return gson.fromJson(response.body!!.string(), SimpsonsCharacterResponse::class.java)
            } catch (e: IOException) {
                Log.d(TAG, "Exception occurred")
            }

            return null
        }

        override fun onPostExecute(result: SimpsonsCharacterResponse) {
            listener?.getDataResponse(result.relatedTopics)
        }
    }
}
