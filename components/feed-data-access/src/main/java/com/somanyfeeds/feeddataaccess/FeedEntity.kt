package com.somanyfeeds.feeddataaccess

data class FeedEntity(
    val id: Long? = null,
    val name: String,
    val slug: String,
    val url: String,
    val type: FeedType
)

enum class FeedType { RSS, ATOM }

fun feedTypeFromString(value: String) =
    if (value.equals("RSS")) FeedType.RSS
    else FeedType.ATOM
