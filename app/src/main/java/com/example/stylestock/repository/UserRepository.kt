package com.example.stylestock.repository

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.URL

class UserRepository(apiKey: String="") {
    val apiKey = apiKey
    val BaseUrl = "http://thegoodnetwork.fr/index.php/api"
    var client: OkHttpClient = OkHttpClient()

    suspend fun getUserById(id: String): String{
        Log.d("styleStock", "getUserById")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization","""Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/users/${id}""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?:""
                if(response.isSuccessful) {
                    return@withContext result
                }else{
                    Log.d("styleStock",result)
                    return@withContext ""
                }
            } catch (err: Error) {
                Log.d("styleStock",err.toString())
                return@withContext ""
            }
        }
    }
    suspend fun getUserByEmail(email: String): String{
        Log.d("styleStock", "getUserByEmail")
        val json = """
            {
                "email": "$email"
            }
        """.trimIndent()
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization","""Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/users?email=${email}""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?:""
                if(response.isSuccessful) {
                    return@withContext result
                }else{
                    Log.d("styleStock",result)
                    return@withContext ""
                }
            } catch (err: Error) {
                Log.d("styleStock",err.toString())
                return@withContext ""
            }
        }
    }

    suspend fun createUser(email: String,password: String): String{
        Log.d("styleStock", "createUser")
        val json = """
            {
                "email": "$email",
                "plainPassword": "$password"
            }
        """.trimIndent()
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + "/users/register")
                val request = Request.Builder()
                    .headers(headers)
                    .post(body)
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?:""

                if(response.isSuccessful) {

                    return@withContext result
                }else{
                    Log.d("styleStock",result)
                    return@withContext ""
                }
            } catch (err: Error) {
                Log.d("styleStock",err.toString())
                return@withContext ""
            }
        }
    }
    suspend fun deleteUserById(id: String): String{
        Log.d("styleStock", "deleteUserById")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization","""Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/users/${id}""")
                val request = Request.Builder()
                    .headers(headers)
                    .delete()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?:""
                if(response.isSuccessful) {
                    return@withContext result
                }else{
                    Log.d("styleStock",result)
                    return@withContext ""
                }
            } catch (err: Error) {
                Log.d("styleStock",err.toString())
                return@withContext ""
            }
        }
    }
}