package com.somanyfeeds.articledataaccess

import java.time.LocalDateTime

data class ArticleEntity(
    val id: Long? = null,
    val title: String,
    val link: String,
    val content: String,
    val date: LocalDateTime
)
