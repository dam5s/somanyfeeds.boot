package com.somanyfeeds.somanyfeedsapplication

import java.time.ZonedDateTime

data class ArticleViewModel(
    val id: Long?,
    val title: String,
    val link: String,
    val content: String,
    val date: ZonedDateTime
)

fun presentEntity(entity: ArticleEntity) = ArticleViewModel(
    entity.id,
    entity.title,
    entity.link,
    entity.content,
    entity.date
)
