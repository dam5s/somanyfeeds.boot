package com.somanyfeeds.feedprocessing

import com.somanyfeeds.articledataaccess.ArticleEntity
import com.somanyfeeds.articledataaccess.ArticleRepository
import com.somanyfeeds.feeddataaccess.FeedEntity
import javax.inject.Inject
import javax.transaction.Transactional

interface ArticleUpdater {
    fun updateArticles(articles: List<ArticleEntity>, feed: FeedEntity)
}

class DefaultArticleUpdater : ArticleUpdater {

    val articleRepo: ArticleRepository
    val articleLimit: Int

    @Inject
    constructor(articleRepo: ArticleRepository, articleLimit: Int) {
        this.articleRepo = articleRepo
        this.articleLimit = articleLimit
    }

    @Transactional
    override fun updateArticles(articles: List<ArticleEntity>, feed: FeedEntity) {
        articleRepo.deleteByFeed(feed)

        var count = 0
        for (article in articles) {
            if (count >= articleLimit) break

            articleRepo.create(article, feed)
            count++
        }
    }
}
