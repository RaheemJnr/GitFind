package com.example.gitfind.data.network.model

import com.example.gitfind.domain.Owner
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * a data transfer object that is used to map domain model data
 */
@JsonClass(generateAdapter = true)
data class GithubDTO(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "owner")
    val owner: Owner,
    @Json(name = "url")
    val url: String,
    @Json(name = "stargazers_count")
    val stargazer_count: Int?,
    @Json(name = "language")
    val language: String?

)