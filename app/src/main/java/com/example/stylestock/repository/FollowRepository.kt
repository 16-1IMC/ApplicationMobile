package com.example.stylestock.repository

import android.util.Log
import com.example.stylestock.modele.Follow
import com.example.stylestock.modele.FollowAll
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.URL

class FollowRepository(apiKey: String = ""){
    val apiKey = apiKey
    val BaseUrl = "http://thegoodnetwork.fr/index.php/api"
    var client: OkHttpClient = OkHttpClient()


    suspend fun getFollow(userId: String, brandId: String): FollowAll?{
        Log.d("styleStock", "getFollow")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/follows?user=${userId}&brand=${brandId}""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                var follow =Gson().fromJson(result, Array<FollowAll>::class.java)
                if (follow.isNotEmpty()) {
                    return@withContext follow[0]
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

    suspend fun addFollower(userId: String, brandId: String): String {
        Log.d("styleStock", "addFollower")
        val json = """
            {
                "brand": "/api/brands/$brandId",
                "user": "/api/users/$userId",
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
                val url = URL(BaseUrl + """/follows""")
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

    suspend fun removeFollow(userId: String, brandId: String): String{
        Log.d("styleStock", "removeFollow")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + "/follows?brandId=${brandId}&userId=${userId}")
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