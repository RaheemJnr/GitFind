package com.example.gitfind.data.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * a data class that is returned by the network
 */
@JsonClass(generateAdapter = true)
data class GithubResponses(
    @Json(name = "total_count")
    val totalCount: Int? = null,
    @Json(name = "incomplete_result")
    val incompleteResult: Int? = null,
    @Json(name = "items")
    val listItems: List<GithubDTO>,
)
