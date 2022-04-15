package com.example.gitfind.repository

import com.example.gitfind.domain.GithubListData


/**
 * interface to get our data
 */
interface GitFindDataRepo {
    suspend fun getGitHubDataList(query: String, page: Int, pageSize: Int): List<GithubListData>
}