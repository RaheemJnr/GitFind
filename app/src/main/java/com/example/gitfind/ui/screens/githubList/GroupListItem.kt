package com.example.gitfind.ui.screens.githubList

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@ExperimentalAnimationApi
@Composable
fun RepoListItem(
    repo: RepoCategory,
    isSelected: Boolean = false,
    onSelectedCategoryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
) {
    Surface(
        modifier = Modifier.padding(8.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = if (isSelected) MaterialTheme.colors.primary else Color.LightGray,

        ) {
        Column(
            modifier =
            Modifier
                .wrapContentSize()
                .padding(8.dp)
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectedCategoryChanged(repo.value)
                        onExecuteSearch()
                    })
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth((0.4f)),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = repo.value,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier,
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}


@ExperimentalAnimationApi
@Composable
fun RepoGroup(
    repo: List<RepoCategory> = getAllRepoCategories(),
    selected: RepoCategory? = null,
    onSelectedChanged: (String) -> Unit = {},
    onExecuteSearch: () -> Unit = {}
) {
    LazyRow {
        itemsIndexed(
            items = repo
        ) { _, item ->
            RepoListItem(
                repo = item,
                isSelected = selected == item,
                onSelectedCategoryChanged = {
                    onSelectedChanged(it)
                },
                onExecuteSearch = { onExecuteSearch() }
            )
        }
    }

}
