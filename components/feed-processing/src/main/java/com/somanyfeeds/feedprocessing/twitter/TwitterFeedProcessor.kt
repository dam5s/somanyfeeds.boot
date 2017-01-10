package com.somanyfeeds.feedprocessing.twitter

import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.feeddataaccess.Feed
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.FeedProcessor
import com.somanyfeeds.feedprocessing.toLocalDateTime
import org.slf4j.LoggerFactory
import org.springframework.social.twitter.api.Tweet
import org.springframework.social.twitter.api.Twitter
import org.springframework.stereotype.Service

@Service
class TwitterFeedProcessor(twitter: Twitter) : FeedProcessor {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val pageSize = 50
    private val tweetsCountToFetch = 20
    private val timelineOperations = twitter.timelineOperations()


    override fun canProcess(feed: Feed) = feed.type == FeedType.TWITTER

    override fun process(feed: Feed): List<Article> {
        logger.debug("Processing Feed: {}", feed)

        val tweets = timelineOperations.getUserTimeline(feed.info, pageSize)

        while (tweets.filtered().count() < tweetsCountToFetch) {
            tweets.fetchMore(feed.info)
        }

        val articles = tweets.filtered().map {
            Article(
                link = "https://twitter.com/${feed.info}",
                date = it.createdAt.toLocalDateTime(),
                content = it.text,
                source = feed.slug
            )
        }

        if (logger.isDebugEnabled) {
            logger.debug("Processed #{} articles", articles.count())
        }

        return articles.toList()
    }


    private fun MutableList<Tweet>.fetchMore(screenName: String) {
        val maxId: Long = map { it.id }.min()!! - 1
        val sinceId: Long = 0

        val moreTweets = timelineOperations.getUserTimeline(screenName, pageSize, sinceId, maxId)

        addAll(moreTweets)
    }

    private fun List<Tweet>.filtered() = asSequence().withoutRetweets().withoutReplies().take(tweetsCountToFetch)

    private fun Sequence<Tweet>.withoutRetweets() = filter { !it.isRetweet }

    private fun Sequence<Tweet>.withoutReplies() = filter { it.inReplyToStatusId == null }
}

