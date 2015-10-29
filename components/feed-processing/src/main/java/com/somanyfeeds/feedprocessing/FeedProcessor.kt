package com.somanyfeeds.feedprocessing

import com.somanyfeeds.articledataaccess.ArticleEntity
import com.somanyfeeds.feeddataaccess.FeedEntity

interface FeedProcessor {
    fun process(feed: FeedEntity): List<ArticleEntity>
}
