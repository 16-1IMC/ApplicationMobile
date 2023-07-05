package com.example.stylestock.repository

import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Headers
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.net.URL

class ImageRepository(apiKey: String = "") {

    val apiKey = apiKey
    val BaseUrl = "http://thegoodnetwork.fr/index.php/api"
    var client: OkHttpClient = OkHttpClient()

    suspend fun createImage(file: File): String {
        Log.d("styleStock", "createImage")
        val builder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)

        val requestBody = file.asRequestBody("image/*".toMediaTypeOrNull())
        builder.addFormDataPart("file", file.name, requestBody)

        val multipartBody = builder.build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/images""")
                val request = Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer $apiKey")
                    .post(multipartBody)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                if (response.isSuccessful) {
                    return@withContext result
                } else {
                    Log.d("styleStock", result)
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

