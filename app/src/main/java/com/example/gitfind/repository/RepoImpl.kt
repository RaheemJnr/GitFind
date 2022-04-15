package com.example.gitfind.repository

import com.example.gitfind.data.DTOMapper
import com.example.gitfind.data.network.GitFindApiCall
import com.example.gitfind.domain.GithubListData


/**
 * repo implementation to that map network data to domains'
 */
class GitFindRepoImpl(private val mapper: DTOMapper) : GitFindDataRepo {

    override suspend fun getGitHubDataList(
        query: String,
        page: Int,
        pageSize: Int
    ): List<GithubListData> {
        val response = GitFindApiCall.GIT_FIND_SERVICE.getGitRepositories(query)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            val cc = mapper.toDomainList(responseBody!!.listItems)
            cc
        } else {
            emptyList()
        }
    }
}


