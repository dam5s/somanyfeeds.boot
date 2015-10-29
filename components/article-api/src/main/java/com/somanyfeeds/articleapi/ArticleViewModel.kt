package com.somanyfeeds.articleapi

import com.somanyfeeds.articledataaccess.ArticleEntity
import java.time.LocalDateTime

data class ArticleViewModel(
    val id: Long?,
    val source: String,
    val title: String,
    val link: String,
    val content: String,
    val date: LocalDateTime
)

fun presentArticle(article: ArticleEntity, feedName: String) = ArticleViewModel(
    id = article.id,
    source = feedName,
    title = article.title,
    link = article.link,
    content = article.content,
    date = article.date
)
