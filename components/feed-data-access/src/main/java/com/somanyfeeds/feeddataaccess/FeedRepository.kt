package com.somanyfeeds.feeddataaccess

import org.springframework.stereotype.Repository
import javax.inject.Inject

interface FeedRepository {
    fun findAll(): Iterable<FeedEntity>
}

@Repository
open class JpaFeedRepository : FeedRepository {

    @Inject
    private lateinit var crudRepo: FeedCrudRepository

    override fun findAll() = crudRepo.findAll().map(::buildFeedEntity)
}

private fun buildFeedEntity(entity: FeedCrudEntity) = FeedEntity(
    id = entity.id,
    name = entity.name,
    slug = entity.slug,
    url = entity.url,
    type = feedTypeFromString(entity.type)
)
