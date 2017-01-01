package com.somanyfeeds.feedprocessing

import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.articledataaccess.ArticleRepository
import com.somanyfeeds.feeddataaccess.Feed
import org.springframework.stereotype.Service

@Service
class ArticleUpdater(val articleRepo: ArticleRepository,
                     val articleLimit: Int) {

    fun updateArticles(articles: List<Article>, feed: Feed) {
        articleRepo.deleteByFeed(feed)

        var count = 0
        for (article in articles) {
            if (count >= articleLimit) break

            articleRepo.create(article, feed)
            count++
        }
    }
}
