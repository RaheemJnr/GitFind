package com.example.gitfind.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.gitfind.domain.GithubListData
import com.example.gitfind.repository.GitFindDataRepo
import kotlinx.coroutines.launch

/**
 * i used the same viewModel for both the list and detail screen because the code is straight forward and not too much
 */
class WeatherViewModel(private val repo: GitFindDataRepo) : ViewModel() {
    // for list
    val weatherDataList: MutableState<List<GithubListData>> = mutableStateOf(listOf())

    //
    val listLoading: MutableState<Boolean> = mutableStateOf(false)


    //launch a coroutine scope, show loading value,fetch data and render the data
    fun addQuery(query: String) {
        viewModelScope.launch {
            listLoading.value = true
            val result = repo.getHarareWeatherDataList(query)
            weatherDataList.value = result
            listLoading.value = false
        }
    }


}


/** viewModel Factory
 * it function is to tell the viewmodel how to
 * create the repo object injected as a dependency
 * */
class AuthViewModelFactory(private val repo: GitFindDataRepo) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(repo) as T
    }
}