package com.example.gitfind.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.gitfind.R
import com.example.gitfind.ui.theme.compositedOnSurface

/**
function to display user profile image
 */
@ExperimentalCoilApi
@Composable
fun CircularImageView(
    uri: String,
    contentDescription: String? = "",
    contentScale: ContentScale = ContentScale.Fit,
    placeholderColor: Color? = MaterialTheme.colors.compositedOnSurface(0.2f),
) {
    Card(
        modifier = Modifier.size(100.dp),
        shape = CircleShape,
        elevation = 22.dp,
        border = BorderStroke(2.dp, Color.Gray)
    ) {
        val painter = rememberImagePainter(
            data = uri,
            builder = {
                placeholder(drawableResId = R.drawable.placeholder_person)
            }
        )
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .clip(CircleShape),
            contentScale = contentScale
        )
        if (painter.state is ImagePainter.State.Loading && placeholderColor != null) {
            Spacer(
                modifier = Modifier
                    .background(placeholderColor)
            )
        }
    }
}