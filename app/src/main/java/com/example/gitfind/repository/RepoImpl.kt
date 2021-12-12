package com.example.gitfind.repository

import com.example.gitfind.data.DTOMapper
import com.example.gitfind.data.network.GitFindApiCall
import com.example.gitfind.domain.GithubListData


/**
 * repo implementation to that map network data to domains'
 */
class GitFindRepoImpl(private val mapper: DTOMapper) : GitFindDataRepo {

    override suspend fun getGitHubDataList(query: String): List<GithubListData> {
        return mapper.toDomainList(
            GitFindApiCall.GIT_FIND_SERVICE.getGitRepositories(query).listItems
        )
    }
}