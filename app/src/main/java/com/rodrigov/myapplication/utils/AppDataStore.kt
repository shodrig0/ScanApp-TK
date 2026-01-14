package com.rodrigov.myapplication.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

object AppDataStore {
    private val SERVER_IP = stringPreferencesKey("server_ip")

    fun getServerIp(context: Context): Flow<String> {
        return context.dataStore.data.map { preferences -> preferences[SERVER_IP] ?: ""
        }
    }

    suspend fun saveServerIp(context: Context, ip: String) {
        context.dataStore.edit { preferences -> preferences[SERVER_IP] = ip
        }
    }
}