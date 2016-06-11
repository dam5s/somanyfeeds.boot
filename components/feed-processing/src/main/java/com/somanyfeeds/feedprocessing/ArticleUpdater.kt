package com.somanyfeeds.feedprocessing

import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.articledataaccess.ArticleRepository
import com.somanyfeeds.feeddataaccess.Feed
import javax.inject.Inject
import javax.transaction.Transactional

interface ArticleUpdater {
    fun updateArticles(articles: List<Article>, feed: Feed)
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
    override fun updateArticles(articles: List<Article>, feed: Feed) {
        articleRepo.deleteByFeed(feed)

        var count = 0
        for (article in articles) {
            if (count >= articleLimit) break

            articleRepo.create(article, feed)
            count++
        }
    }
}
