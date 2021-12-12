package com.example.gitfind.ui.screens.githubList

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.example.gitfind.data.DTOMapper
import com.example.gitfind.repository.GitFindRepoImpl
import com.example.gitfind.ui.viewModel.AuthViewModelFactory
import com.example.gitfind.ui.viewModel.GitFindViewModel
import com.example.gitfind.utils.LoaderDialog

//
val selectedRepo: MutableState<RepoCategory?> = mutableStateOf(null)

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun GitHubListScreen() {
    // reference to viewModel
    val gitFindViewModel: GitFindViewModel = viewModel(
        factory = AuthViewModelFactory(GitFindRepoImpl(DTOMapper()))
    )
    // weatherViewModel.addQuery("android")
    //
    val weather = gitFindViewModel.repos.value
    val query = gitFindViewModel.query.value
    val selectedRepo = gitFindViewModel.selectedRepo.value
    val loading = gitFindViewModel.listLoading.value

    Surface() {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            //search bar
            Surface(
                modifier = Modifier.fillMaxWidth(),
                // color = MaterialTheme.colors.primary,
                elevation = 8.dp
            ) {
                val focusManager = LocalFocusManager.current
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(8.dp),
                        value = query,
                        onValueChange = {
                            gitFindViewModel.onQueryChanged(it)
                        },
                        label = {
                            Text(text = "Search")
                        },
                        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                gitFindViewModel.addQuery()
//                                Log.d("keyboardOptions", "$query result")
//                                Log.d("keyboardOptions", "$weather result")
                                focusManager.clearFocus()
                            }
                        ),
                        textStyle = TextStyle(
//                            color = MaterialTheme.colors.onSurface,
//                            background = MaterialTheme.colors.surface
                        )
                    )
                }
            }
            // chip group
            RepoGroup(
                selected = selectedRepo,
                onSelectedChanged = {
                    gitFindViewModel.onSelectedRepoChanged(it)
                },
                onExecuteSearch = gitFindViewModel::addQuery
            )
            if (loading) {
                LoaderDialog()

            } else {
                //
                LazyColumn() {
                    itemsIndexed(
                        items = weather
                    ) { _, item ->
                        RepoListItem(
                            repoData = item
                        )
                    }
                }
            }
        }
    }

}