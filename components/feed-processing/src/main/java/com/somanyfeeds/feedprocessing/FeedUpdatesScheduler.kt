package com.somanyfeeds.feedprocessing;

import org.slf4j.LoggerFactory
import java.util.concurrent.*
import javax.inject.Inject

interface FeedUpdatesScheduler {
    fun start()
    fun stop()
}

class DefaultFeedUpdatesScheduler : FeedUpdatesScheduler {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val scheduledExecutorService: ScheduledExecutorService
    private val feedsUpdater: Runnable

    @Inject
    constructor(scheduledExecutorService: ScheduledExecutorService, feedsUpdater: Runnable) {
        this.scheduledExecutorService = scheduledExecutorService
        this.feedsUpdater = feedsUpdater
    }

    var future: ScheduledFuture<out Any?>? = null

    override fun start() {
        future = scheduledExecutorService.scheduleAtFixedRate({
            try {
                feedsUpdater.run()
            } catch (e: Exception) {
                logger.error("There was an error when updating feeds", e)
            }
        }, 1, 5, TimeUnit.SECONDS)
    }

    override fun stop() {
        future?.cancel(true)
    }
}
