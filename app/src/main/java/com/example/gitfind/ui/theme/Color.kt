package com.example.gitfind.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

val PrimaryColor = Color(0xFF4db6ac)
val primaryLightColor = Color(0xFF82e9de)
val primaryDarkColor = Color(0xFF00867d)
val secondaryColor = Color(0xFFf4511e)
val secondaryLightColor = Color(0xFFff844c)
val secondaryDarkColor = Color(0xFFb91400)
val primaryTextColor = Color(0xFF000000)
val secondaryTextColor = Color(0xFF000000)


/**
 * Return the fully opaque color that results from compositing [onSurface] atop [surface] with the
 * given [alpha]. Useful for situations where semi-transparent colors are undesirable.
 */
@Composable
fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}