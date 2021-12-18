package com.example.gitfind.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetDarkMode(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Dark Theme")
        val IS_DARK = booleanPreferencesKey("isDark")
    }

    //get the saved email
    val getIsDarkTheme: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_DARK] ?: false
        }

    //save email into datastore
    suspend fun saveIsDarkTheme(bool: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_DARK] = bool
        }
    }


}