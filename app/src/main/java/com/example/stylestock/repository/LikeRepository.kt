package com.example.stylestock.repository

import android.util.Log
import com.example.stylestock.modele.LikeAll
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.URL

class LikeRepository(apiKey: String = "") {
    val apiKey = apiKey
    val BaseUrl = "http://thegoodnetwork.fr/index.php/api"
    var client: OkHttpClient = OkHttpClient()

    suspend fun getLike(userId: String, postId: String): LikeAll?{
        Log.d("styleStock", "getLike")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/likes?user=${userId}&brand=${postId}""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                Log.d("styleStock", "user : $userId, brand : $postId, result : $result")
                var like = Gson().fromJson(result, Array<LikeAll>::class.java)
                if (like.isNotEmpty()) {
                    return@withContext like[0]
                } else {
                    Log.d("styleStock", "Err : $result")
                    return@withContext null
                }
            } catch (err: Error) {
                Log.d("styleStock", err.toString())
                return@withContext null
            }
        }
    }

    suspend fun addLike(userId: String, postId: String): String {
        Log.d("styleStock", "addLike")
        val json = """
            {
                "postId": "/api/brands/$postId",
                "userId": "/api/users/$userId",
                "created_at": "2021-04-20T14:00:00+00:00"
            }
        """.trimIndent()
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/likes""")
                val request = Request.Builder()
                    .headers(headers)
                    .post(body)
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                if (response.isSuccessful) {
                    return@withContext result
                } else {
                    Log.d("styleStock", result)
                    return@withContext ""
                }
            } catch (err: Error) {
                Log.d("styleStock", err.toString())
                return@withContext ""
            }
        }
    }

    suspend fun removeLike(userId: String, postId: String): String{
        Log.d("styleStock", "removeLike")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                var like = getLike(userId, postId)
                val url = URL(BaseUrl + "/likes/${like?.id}")
                val request = Request.Builder()
                    .headers(headers)
                    .delete()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                if (response.isSuccessful) {
                    return@withContext result
                } else {
                    Log.d("styleStock", result)
                    return@withContext ""
                }
            } catch (err: Error) {
                Log.d("styleStock", err.toString())
                return@withContext ""
            }
        }

    }
}