package com.example.gitfind.ui.screens.githubList

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.example.gitfind.domain.GithubListData
import com.example.gitfind.ui.screens.networkImage.CircularImageView
import java.util.*

@ExperimentalCoilApi
@Composable
fun RepoListItem(
    repoData: GithubListData
) {
    val animatedProgress = remember { Animatable(initialValue = 0.8f) }
    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(300, easing = LinearEasing)
        )
    }
    Surface(
        elevation = 8.dp,
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .graphicsLayer(scaleY = animatedProgress.value, scaleX = animatedProgress.value)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                text = repoData.name ?: "HH",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //owner name
                Text(
                    modifier = Modifier,
                    text = repoData.language ?: "Some Awesome Language..",
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace
                )
                //language and star
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Bottom),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = if (repoData.stargazers_count!! > 1)
                            repoData.stargazers_count.toString() else (0).toString(),
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        Icons.Filled.Star,
                        contentDescription = "",
                        tint = Color(0xffffd700),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(18.dp)

                    )
                }
            }
            //
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                //
                CircularImageView(uri = repoData.owner.avatar_url)
                Spacer(modifier = Modifier.width(22.dp))
                Text(
                    text = repoData.owner.login.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    fontSize = 22.sp,
                    fontWeight = FontWeight(500),
                    maxLines = 2,
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Preview(showBackground = true)
@Composable
fun RepoListItemPrev() {
//    RepoListItem(
//    )
}