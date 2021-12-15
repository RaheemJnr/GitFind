package com.example.gitfind.ui.screens.githubList

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.annotation.ExperimentalCoilApi
import com.example.gitfind.BaseApplication
import com.example.gitfind.data.DTOMapper
import com.example.gitfind.repository.GitFindRepoImpl
import com.example.gitfind.ui.theme.GitFindTheme
import com.example.gitfind.ui.viewModel.AuthViewModelFactory
import com.example.gitfind.ui.viewModel.GitFindViewModel
import com.example.gitfind.utils.LoaderDialog

//
//val selectedRepo: MutableState<RepoCategory?> = mutableStateOf(null)

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

    //base application -> refactor
    val application = BaseApplication()
    //
    GitFindTheme(application.isDark.value) {
        Surface() {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                //search bar
                SearchBar(
                    query,
                    gitFindViewModel,
                    onToggleTheme = { application.toggleTheme() }
                )
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

}

@Composable
private fun SearchBar(
    query: String,
    gitFindViewModel: GitFindViewModel,
    onToggleTheme: () -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface,
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
                leadingIcon = { Icon(
                    Icons.Filled.Search, contentDescription = "",
                    tint = MaterialTheme.colors.onSurface
                ) },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        gitFindViewModel.addQuery()
                        focusManager.clearFocus()
                    }
                ),
                textStyle = TextStyle(
//                            color = MaterialTheme.colors.onSurface,
//                            background = MaterialTheme.colors.surface
                )
            )
            //
            ConstraintLayout(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                val menu = createRef()
                IconButton(
                    onClick = { onToggleTheme() },
                    modifier = Modifier.constrainAs(menu) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                    Icon(Icons.Filled.DarkMode, contentDescription = "")
                }

            }
        }
    }
}