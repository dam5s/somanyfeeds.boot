package com.somanyfeeds.feedprocessing.twitter

import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.feeddataaccess.Feed
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.FeedProcessor
import com.somanyfeeds.feedprocessing.toLocalDateTime
import org.slf4j.LoggerFactory
import org.springframework.social.twitter.api.Twitter
import org.springframework.stereotype.Service
import javax.inject.Inject

@Service
class TwitterFeedProcessor
@Inject
constructor(private val twitter: Twitter) : FeedProcessor {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun canProcess(feed: Feed) = feed.type == FeedType.TWITTER

    override fun process(feed: Feed): List<Article> {
        logger.debug("Processing Feed: {}", feed)

        val timelineOperations = twitter.timelineOperations()
        val tweets = timelineOperations.getUserTimeline(feed.info)

        val articles = tweets.map {
            Article(
                link = "https://twitter.com/${feed.info}",
                date = it.createdAt.toLocalDateTime(),
                content = it.text,
                source = feed.slug
            )
        }

        logger.debug("Processed #{} articles", articles.size)
        return articles
    }
}
