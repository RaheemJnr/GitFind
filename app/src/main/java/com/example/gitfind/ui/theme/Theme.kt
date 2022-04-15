package com.example.gitfind.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = primaryDarkColor,
    primaryVariant = primaryLightColor,
    secondary = secondaryColor,
    secondaryVariant = secondaryLightColor,
    onError = Color.Red,
    onPrimary = Color.White,
    onBackground = Color.White,
    onSecondary = Color.White,
    onSurface = Color.White

)

private val LightColorPalette = lightColors(
    primary = PrimaryColor,
    primaryVariant = primaryLightColor,
    secondary = secondaryDarkColor,
    secondaryVariant = secondaryLightColor,
    onError = Color.Red,
    onPrimary = Color.White,
    onBackground = Color.Black,
    onSecondary = Color.Black,
    onSurface = Color.Black
)

@Composable
fun GitFindTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }


    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}