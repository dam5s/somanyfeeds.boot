package com.somanyfeeds.articledataaccess

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory
import org.springframework.stereotype.Repository
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

interface ArticleRepository {
    fun findAll(): Iterable<ArticleEntity>
}

@Repository
open class JpaArticleRepository : ArticleRepository {

    val crudRepo: ArticleCrudRepository

    @Inject
    constructor(crudRepo: ArticleCrudRepository) {
        this.crudRepo = crudRepo
    }

    constructor(factory: JpaRepositoryFactory) {
        this.crudRepo = factory.getRepository(ArticleCrudRepository::class.java)
    }

    override fun findAll(): Iterable<ArticleEntity> = crudRepo.findAll().map(::buildArticleEntity)
}

fun buildArticleEntity(entity: ArticleCrudEntity) = ArticleEntity(
    id = entity.id,
    title = entity.title,
    link = entity.link,
    content = entity.content,
    date = entity.date.toZonedDateTime()
)

fun Date.toZonedDateTime() = toInstant().atZone(ZoneId.systemDefault())
