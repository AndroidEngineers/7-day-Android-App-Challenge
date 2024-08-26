package com.android.engineer.mealmate.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.android.engineer.mealmate.data.utils.DATASTORE_NAME
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATASTORE_NAME)
class MealDataStoreImpl @Inject constructor(private val context: Context): MealDataStore {
    override suspend fun putString(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        val preferenceKey = booleanPreferencesKey(key)
        context.dataStore.edit {
            it[preferenceKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        return try {
            val preferenceKey = stringPreferencesKey(key)
            val preference = context.dataStore.data.first()
            preference[preferenceKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getBoolean(key: String): Boolean {
        return try {
            val preferenceKey = booleanPreferencesKey(key)
            val preference = context.dataStore.data.first()
            preference[preferenceKey] == true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun clearPreference() {
        context.dataStore.edit {
            it.clear()
        }
    }
}