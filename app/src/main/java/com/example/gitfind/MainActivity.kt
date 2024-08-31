package com.example.gitfind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import com.example.gitfind.navigation.MainScreenNavigation
import com.example.gitfind.ui.theme.GitFindTheme
import com.example.gitfind.utils.GetDarkMode

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val context = LocalContext.current
            val localStore = GetDarkMode(context)
            val isDark = localStore.getIsDarkTheme.collectAsState(initial = false)
            //base application // refactor
            GitFindTheme(isDark.value) {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreenNavigation()
                }
            }
        }
    }
}
