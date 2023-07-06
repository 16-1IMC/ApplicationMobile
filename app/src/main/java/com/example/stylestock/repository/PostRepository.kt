package com.example.stylestock.repository

import android.util.Log
import com.example.stylestock.modele.Brand
import com.example.stylestock.modele.Like
import com.example.stylestock.modele.Post
import com.example.stylestock.modele.PostCreate
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.URL

class PostRepository(apiKey: String="") {
    val apiKey = apiKey
    val BaseUrl = "http://thegoodnetwork.fr/index.php/api"
    var client: OkHttpClient = OkHttpClient()


    suspend fun createPost(title:String,content:String,author:String,images:List<String>):PostCreate?{
        Log.d("styleStock", "createBrand")
        var requestFormat = "\"/api/images/"+images.reduce { acc, s -> acc+"\",\"/api/images/$s" }+"\""
        val json="""
                {
                  "title": "$title",
                  "content": "$content",
                  "author": "/api/brands/$author",
                  "images": [
                    $requestFormat
                  ]
                }
        """.trimIndent()
        Log.d("styleStock", json)
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO){
            var result: String? = null
            try {
                val url = URL("$BaseUrl/posts")
                val request = Request.Builder()
                    .headers(headers)
                    .post(body)
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                var post = Gson().fromJson(result, PostCreate::class.java)
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

    suspend fun getPostByBrandIdMultiple(brandsId: List<String>): List<Post> {
        Log.d("styleStock", "getPostByBrandIdMultiple")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                var requestFormat = "author%5B%5D="+brandsId.reduce { acc, s -> acc+"&author%5B%5D=$s" }
                var url = URL(BaseUrl + """/posts?$requestFormat""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                Log.d("styleStock", result)
                val posts = Gson().fromJson(result, Array<Post>::class.java)
                if (response.isSuccessful) {
                    return@withContext posts.toList()
                } else {
                    Log.d("styleStock", result)
                    return@withContext emptyList()
                }
            } catch (err: Error) {
                Log.d("styleStock", err.toString())
                return@withContext emptyList()
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
                val url = URL(BaseUrl + """/likes?post_id=$id""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                Log.d("styleStock", result)
                val nbLike = Gson().fromJson(result, Array<Like>::class.java)
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