package com.somanyfeeds.articledataaccess

import com.somanyfeeds.feeddataaccess.FeedEntity

interface ArticleRepository {
    fun findAll(): Iterable<ArticleEntity>

    fun findAllBySlugs(slugs: List<String>): Iterable<ArticleEntity>

    fun create(article: ArticleEntity, feed: FeedEntity): Long

    fun deleteByFeed(feed: FeedEntity)
}
