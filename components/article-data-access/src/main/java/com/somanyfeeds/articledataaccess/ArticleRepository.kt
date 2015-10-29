package com.somanyfeeds.articledataaccess

import com.somanyfeeds.feeddataaccess.FeedEntity
import org.springframework.stereotype.Repository
import javax.inject.Inject

interface ArticleRepository {
    fun findAll(): Iterable<ArticleEntity>

    fun create(article: ArticleEntity, feed: FeedEntity): Long

    fun deleteByFeed(feed: FeedEntity)
}

@Repository
open class JpaArticleRepository : ArticleRepository {

    @Inject
    private lateinit var crudRepo: ArticleCrudRepository

    override fun findAll() = crudRepo.findAll().map(::buildArticleEntity)

    override fun create(article: ArticleEntity, feed: FeedEntity): Long {
        val crudEntity = ArticleCrudEntity().apply {
            feedId = feed.id!!
            title = article.title
            link = article.link
            content = article.content
            date = article.date
        }

        return crudRepo.save(crudEntity).id
    }

    override fun deleteByFeed(feed: FeedEntity) = crudRepo.deleteByFeedId(feed.id)
}

private fun buildArticleEntity(entity: ArticleCrudEntity) = ArticleEntity(
    id = entity.id,
    title = entity.title,
    link = entity.link,
    content = entity.content,
    date = entity.date
)
