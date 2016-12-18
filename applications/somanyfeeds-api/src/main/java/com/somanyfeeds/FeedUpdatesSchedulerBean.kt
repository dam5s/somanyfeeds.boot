package com.somanyfeeds

import com.somanyfeeds.feedprocessing.FeedUpdatesScheduler
import com.somanyfeeds.feedprocessing.FeedsUpdater
import org.springframework.beans.factory.DisposableBean
import org.springframework.stereotype.Service
import java.util.concurrent.ScheduledExecutorService


@Service
class FeedUpdatesSchedulerBean(execService: ScheduledExecutorService, feedsUpdater: FeedsUpdater) : DisposableBean {

    val scheduler = FeedUpdatesScheduler(execService, feedsUpdater)

    init {
        scheduler.start()
    }

    override fun destroy() {
        scheduler.stop()
    }
}
