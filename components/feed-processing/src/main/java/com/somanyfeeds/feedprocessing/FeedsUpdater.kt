package com.somanyfeeds.feedprocessing

import com.somanyfeeds.feeddataaccess.FeedRepository
import com.somanyfeeds.feeddataaccess.FeedType
import javax.inject.Inject


class FeedsUpdater : Runnable {

    val feedRepository: FeedRepository
    val articleUpdater: ArticleUpdater
    val feedProcessors: Map<FeedType, FeedProcessor>

    @Inject
    constructor(
        feedRepository: FeedRepository,
        articleUpdater: ArticleUpdater,
        feedProcessors: Map<FeedType, FeedProcessor>
    ) {
        this.feedRepository = feedRepository
        this.articleUpdater = articleUpdater
        this.feedProcessors = feedProcessors
    }

    override fun run() {
        feedRepository.findAll().forEach { feed ->
            val processor = feedProcessors.get(feed.type)
            val articles = processor!!.process(feed)

            articleUpdater.updateArticles(articles, feed)
        }
    }
}
