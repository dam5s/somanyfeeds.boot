package com.somanyfeeds.articledataaccess

import com.somanyfeeds.feeddataaccess.Feed

interface ArticleRepository {
    fun findAll(): Iterable<Article>

    fun findAllBySlugs(slugs: List<String>): Iterable<Article>

    fun create(article: Article, feed: Feed): Long

    fun deleteByFeed(feed: Feed)
}
