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

//mapper.toDomainList(
//GitFindApiCall.GIT_FIND_SERVICE.getGitRepositories(query)
//)

//override suspend fun getPageCryptos(page: Int, pageSize: Int): List<Crypto> {
//    val response = cryptoApi.getAllCrypto(page)
//    return if (response.isSuccessful && !response.body().isNullOrEmpty()) {
//        val cryptoApiResponseList = response.body()
//        val cryptoList = cryptoApiResponseList?.map { cryptoApiResponse ->
//            cryptoApiMapper.map(cryptoApiResponse)
//        }
//        cryptoList ?: emptyList()
//    } else {
//        emptyList()
//    }
//}