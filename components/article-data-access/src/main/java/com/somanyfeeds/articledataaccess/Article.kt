package com.somanyfeeds.articledataaccess

import java.time.LocalDateTime

data class Article(
    val id: Long? = null,
    val title: String? = null,
    val link: String,
    val content: String,
    val date: LocalDateTime,
    val source: String? = null
)
