package com.example.stylestock.repository

import android.util.Log
import com.example.stylestock.modele.ApiKey
import com.example.stylestock.modele.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.FormBody
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.net.URL
import java.text.Normalizer.Form


class ApiRepository(apiKey: String="") {
    val apiKey = apiKey
    val BaseUrl = "http://thegoodnetwork.fr/index.php/api"
    var client: OkHttpClient = OkHttpClient()

    suspend fun getToken(email: String, password: String): String {
        Log.d("styleStock", "getToken")
        val json = """
            {
                "email": "$email",
                "password": "$password"
            }
        """.trimIndent()
        val body = json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + "/auth")
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



}