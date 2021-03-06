package com.somanyfeeds.feedprocessing.atom

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.somanyfeeds.articledataaccess.Article
import com.somanyfeeds.feeddataaccess.Feed
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.FeedProcessor
import com.somanyfeeds.feedprocessing.toLocalDateTime
import com.somanyfeeds.httpgateway.HttpGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AtomFeedProcessor(val httpGateway: HttpGateway) : FeedProcessor {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    private val xmlMapper = XmlMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }


    override fun canProcess(feed: Feed) = (feed.type == FeedType.ATOM)

    override fun process(feed: Feed): List<Article> {
        logger.debug("Processing Feed: {}", feed)

        val atomString = httpGateway.get(feed.info)
        val atom = xmlMapper.readValue(atomString, Atom::class.java)
        val articles = atom.entries.map {
            Article(
                title = it.title.text,
                link = it.link.href,
                date = it.published.toLocalDateTime(),
                content = it.content.text,
                source = feed.slug
            )
        }

        logger.debug("Processed #{} articles", articles.size)
        return articles
    }
}
