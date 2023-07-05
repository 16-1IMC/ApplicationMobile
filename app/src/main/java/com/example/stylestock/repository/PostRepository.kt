package com.example.stylestock.repository

import android.util.Log
import com.example.stylestock.modele.Brand
import com.example.stylestock.modele.Post
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class PostRepository(apiKey: String="") {
    val apiKey = apiKey
    val BaseUrl = "http://thegoodnetwork.fr/index.php/api"
    var client: OkHttpClient = OkHttpClient()

    suspend fun getPostById(id: String): Post? {
        Log.d("styleStock", "getPostById")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/posts/$id""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                Log.d("styleStock", result)
                val post = Gson().fromJson(result, Post::class.java)
                if (response.isSuccessful) {
                    return@withContext post
                } else {
                    Log.d("styleStock", result)
                    return@withContext null
                }
            } catch (err: Error) {
                Log.d("styleStock", err.toString())
                return@withContext null
            }
        }

    }

    suspend fun getNbLike(id: String): Int {
        Log.d("styleStock", "getNbLike")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/likes?postid=$id""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                Log.d("styleStock", result)
                val nbLike = Gson().fromJson(result, Array<String>::class.java)
                if (response.isSuccessful) {
                    return@withContext nbLike.size
                } else {
                    Log.d("styleStock", result)
                    return@withContext 0
                }
            } catch (err: Error) {
                Log.d("styleStock", err.toString())
                return@withContext 0
            }
        }

    }
}