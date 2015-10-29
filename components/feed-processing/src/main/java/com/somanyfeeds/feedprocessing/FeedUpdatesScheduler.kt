package com.somanyfeeds.feedprocessing;

import java.util.concurrent.*
import javax.inject.Inject

interface FeedUpdatesScheduler {
    fun start()
    fun stop()
}

class DefaultFeedUpdatesScheduler : FeedUpdatesScheduler {

    val scheduledExecutorService: ScheduledExecutorService
    val feedsUpdater: Runnable

    @Inject
    constructor(scheduledExecutorService: ScheduledExecutorService, feedsUpdater: Runnable) {
        this.scheduledExecutorService = scheduledExecutorService
        this.feedsUpdater = feedsUpdater
    }

    var future: ScheduledFuture<out Any?>? = null

    override fun start() {
        future = scheduledExecutorService.scheduleAtFixedRate(feedsUpdater, 0, 5, TimeUnit.SECONDS)
    }

    override fun stop() {
        future?.cancel(true)
    }
}
