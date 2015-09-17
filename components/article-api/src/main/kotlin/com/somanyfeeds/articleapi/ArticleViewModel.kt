package com.somanyfeeds.articleapi

import com.somanyfeeds.articledataaccess.ArticleEntity
import java.time.ZonedDateTime

data class ArticleViewModel(
    val id: Long?,
    val source: String,
    val title: String,
    val link: String,
    val content: String,
    val date: ZonedDateTime
)

fun presentArticle(article: ArticleEntity, feedName: String) = ArticleViewModel(
    id = article.id,
    source = feedName,
    title = article.title,
    link = article.link,
    content = article.content,
    date = article.date
)
