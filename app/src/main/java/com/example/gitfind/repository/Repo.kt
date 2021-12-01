package com.example.gitfind.repository

import com.example.gitfind.domain.GithubListData


/**
 * interface to get our data
 */
interface GitFindDataRepo {
    suspend fun getHarareWeatherDataList(query: String): List<GithubListData>

    // suspend fun getHarareWeatherDataDetails(): List<GithubListData>
}