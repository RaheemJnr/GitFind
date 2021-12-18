package com.example.gitfind.ui.viewModel

import PageNumSource
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.gitfind.domain.GithubListData
import com.example.gitfind.repository.GitFindDataRepo
import com.example.gitfind.ui.screens.githubList.RepoCategory
import com.example.gitfind.ui.screens.githubList.getRepoCategory

/**
 * i used the same viewModel for both the list and detail screen because the code is straight forward and not too much
 */
class GitFindViewModel(private val repo: GitFindDataRepo) : ViewModel() {
    // for list
    val repos: MutableState<List<GithubListData>> = mutableStateOf(listOf())

    val query = mutableStateOf("Android")

    //
    val listLoading: MutableState<Boolean> = mutableStateOf(false)

    val selectedRepo: MutableState<RepoCategory?> = mutableStateOf(null)
    val error: MutableState<Boolean> = mutableStateOf(false)

    init {
        addQuery()
    }

    //    //launch a coroutine scope, show loading value,fetch data and render the data
//    fun addQuery() {
//        viewModelScope.launch {
//            try {
//                listLoading.value = true
//                val result = repo.getGitHubDataList(query.value)
//                repos.value = result
//                listLoading.value = false
//            } catch (e: Exception) {
//                Log.d("Server Error", e.toString())
//                error.value = true
//            }
//
//        }
//    }
// launch a coroutine scope, show loading value,fetch data and render the data
    fun addQuery(pageSize: Int = 20) =
        Pager(config = PagingConfig(pageSize = pageSize, initialLoadSize = pageSize)) {
            PageNumSource { pageNum, pageSize ->
                repo.getGitHubDataList(query.value, pageNum, pageSize)
            }
        }.flow.cachedIn(viewModelScope)


    //
    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    //
    fun onSelectedRepoChanged(repo: String) {
        val newRepo = getRepoCategory(repo)
        selectedRepo.value = newRepo
        onQueryChanged(repo)
    }


    /** viewModel Factory
     * it function is to tell the viewmodel how to
     * create the repo object injected as a dependency
     * */
    class GitFindViewModelFactory(private val repo: GitFindDataRepo) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return GitFindViewModel(repo) as T
        }
    }
}