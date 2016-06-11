package com.somanyfeeds.feedprocessing

import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.feeddataaccess.Feed

interface FeedProcessor {

    fun canProcess(feed: Feed): Boolean

    fun process(feed: Feed): List<Article>
}
