package com.somanyfeeds.feeddataaccess

data class Feed(
    val id: Long? = null,
    val name: String,
    val slug: String,
    val url: String,
    val type: FeedType
)

enum class FeedType { RSS, ATOM, TWITTER, CUSTOM }

fun feedTypeFromString(value: String) =
    when (value) {
        "RSS" -> FeedType.RSS
        "ATOM" -> FeedType.ATOM
        "TWITTER" -> FeedType.TWITTER
        else -> FeedType.CUSTOM
    }
