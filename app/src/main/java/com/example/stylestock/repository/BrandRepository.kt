package com.example.stylestock.repository

import android.util.Log
import com.example.stylestock.modele.Brand
import com.example.stylestock.modele.BrandAll
import com.example.stylestock.modele.FollowAll
import com.example.stylestock.modele.Image
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.URL

class BrandRepository(apiKey: String = "") {

    val apiKey = apiKey
    val BaseUrl = "http://thegoodnetwork.fr/index.php/api"
    var client: OkHttpClient = OkHttpClient()

    suspend fun getBrandAll(): Array<Brand>? {
        Log.d("styleStock", "getBrands")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/brands?order%5Bcreated_at%5D=asc&status=APPROVED""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                val brands = Gson().fromJson(result, Array<BrandAll>::class.java)
                if (response.isSuccessful && brands != null){
                    return@withContext BrandAllToBrands(brands)
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

    suspend fun getBrandByName(name:String) : Array<Brand>? {
        Log.d("styleStock", "getBrandByName")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/brands?name=$name&order%5Bcreated_at%5D=asc&status=APPROVED""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                val brands = Gson().fromJson(result, Array<BrandAll>::class.java)
                if (response.isSuccessful && brands != null){
                    return@withContext BrandAllToBrands(brands)
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

    suspend fun getBrandById(id: String): Brand? {
        Log.d("styleStock", "getBrand")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/brands/$id""")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                val brand = Gson().fromJson(result, Brand::class.java)
                if (response.isSuccessful) {
                    return@withContext brand
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



    suspend fun deleteBrandById(id: String): String {
        Log.d("styleStock", "deleteBrandById")
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + """/brands/${id}""")
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

    suspend fun getNbFollower(brandId:String):Int{
        Log.d("styleStock", "getNbFollower")
        var brandId = brandId;
        val headers = Headers.Builder()
            .add("Accept", "application/json")
            .add("Authorization", """Bearer ${this.apiKey}""")
            .build()
        return withContext(Dispatchers.IO) {
            var result: String? = null
            try {
                val url = URL(BaseUrl + "/follows?brand=$brandId")
                val request = Request.Builder()
                    .headers(headers)
                    .get()
                    .url(url)
                    .build()
                val response = client.newCall(request).execute()
                result = response.body?.string() ?: ""
                val followers = Gson().fromJson(result, Array<FollowAll>::class.java)
                if (response.isSuccessful) {
                    return@withContext followers.size
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

fun BrandAllToBrands(brandAll: Array<BrandAll>): Array<Brand> {
    var brands: Array<Brand> = emptyArray()
    for (brand in brandAll) {
        brands += Brand(
            id = brand.id,
            name = brand.name,
            logo = Image(
                0,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/2048px-Minecraft_missing_texture_block.svg.png",
                ""
            ),
            banner = Image(
                0,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/2048px-Minecraft_missing_texture_block.svg.png",
                ""
            ),
            categories = brand.categories,

            )
    }
    return brands


}