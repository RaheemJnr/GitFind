package com.example.gitfind.domain

/**
 * domain model that model response from server and what to display in the UI
 */
data class GithubListData(
    val id: Int? = null,
    val name: String? = null,
    val owner: Owner,
    val url: String,
    val stargazers_count: Int?,
    val language: String?

)

data class Owner(
    val login: String,
    val avatar_url: String

)