package com.somanyfeeds.feedprocessing.rss

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.feeddataaccess.Feed
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.FeedProcessor
import com.somanyfeeds.feedprocessing.toLocalDateTime
import com.somanyfeeds.httpgateway.HttpGateway
import org.slf4j.LoggerFactory

class RssFeedProcessor(private val httpGateway: HttpGateway) : FeedProcessor {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val xmlMapper = XmlMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    override fun canProcess(feed: Feed) = (feed.type == FeedType.RSS)

    override fun process(feed: Feed): List<Article> {
        logger.debug("Processing Feed: {}", feed)

        val rssString = httpGateway.get(feed.url).replace("\uFEFF", "")
        val rss = xmlMapper.readValue(rssString, Rss::class.java)
        val articles = rss.channel.items.map {
            Article(
                title = it.title,
                link = it.link,
                date = it.pubDate.toLocalDateTime(),
                content = it.description,
                source = null
            )
        }

        logger.debug("Processed #{} articles", articles.size)
        return articles
    }
}
