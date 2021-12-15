package com.example.gitfind

import android.app.Application
import androidx.compose.runtime.mutableStateOf

class BaseApplication : Application() {

    val isDark = mutableStateOf(true)

    fun toggleTheme(){
        isDark.value = isDark.value.not()
    }
}