package com.somanyfeeds

import com.somanyfeeds.feedprocessing.*
import org.springframework.beans.factory.DisposableBean
import org.springframework.stereotype.Service
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject


@Service
class FeedUpdatesSchedulerBean : DisposableBean {

    val scheduler: FeedUpdatesScheduler

    @Inject
    constructor(execService: ScheduledExecutorService, feedsUpdater: FeedsUpdater) {
        scheduler = DefaultFeedUpdatesScheduler(execService, feedsUpdater)
        scheduler.start()
    }

    override fun destroy() {
        scheduler.stop()
    }
}
