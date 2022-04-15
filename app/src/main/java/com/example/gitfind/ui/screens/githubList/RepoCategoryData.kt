package com.example.gitfind.ui.screens.githubList

enum class RepoCategory(val value: String) {
    ANDROID("Android"),
    KOTLIN("Kotlin"),
    FLUTTER("Flutter"),
    JAVA("Java"),
    PHP("Php"),
    C("C"),
    JAVASCRIPT("JavaScript"),
    SWIFT("Swift"),
    IOS("ios"),
}

fun getAllRepoCategories(): List<RepoCategory> {
    return listOf(
        RepoCategory.ANDROID, RepoCategory.KOTLIN,
        RepoCategory.FLUTTER, RepoCategory.JAVA, RepoCategory.PHP,
        RepoCategory.C, RepoCategory.JAVASCRIPT, RepoCategory.SWIFT, RepoCategory.IOS
    )

}

fun getRepoCategory(value: String):
        RepoCategory? {
    val map = RepoCategory.values()
        .associateBy(RepoCategory::value)
    return map[value]
}
