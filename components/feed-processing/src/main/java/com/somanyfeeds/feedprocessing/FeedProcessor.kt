package com.somanyfeeds.feedprocessing

import com.somanyfeeds.articledataaccess.ArticleEntity
import com.somanyfeeds.feeddataaccess.FeedEntity

interface FeedProcessor {

    fun canProcess(feed: FeedEntity): Boolean

    fun process(feed: FeedEntity): List<ArticleEntity>
}
