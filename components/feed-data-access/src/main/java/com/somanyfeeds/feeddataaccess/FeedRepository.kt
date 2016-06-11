package com.somanyfeeds.feeddataaccess

interface FeedRepository {
    fun findAll(): Iterable<Feed>
}
