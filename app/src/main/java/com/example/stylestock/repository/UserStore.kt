package com.example.stylestock.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("userToken")
        private val USER_TOKEN_KEY = stringPreferencesKey("user_token")
        private val USER_ID_KEY = stringPreferencesKey("user_id")
        private val USER_IS_BRAND = booleanPreferencesKey("user_is_brand")
    }

    val getAccessToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN_KEY] ?: ""
    }

    val getUserId: Flow<String>  = context.dataStore.data.map { preferences ->
        preferences[USER_ID_KEY] ?: ""
    }

    val getIsBrand: Flow<Boolean>  = context.dataStore.data.map { preferences ->
        preferences[USER_IS_BRAND] ?: false
    }

    suspend fun clearToken(){
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    suspend fun saveToken(token: String, id: String, isBrand: Boolean){
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
            preferences[USER_ID_KEY] = id
            preferences[USER_IS_BRAND] = isBrand
        }
    }
}