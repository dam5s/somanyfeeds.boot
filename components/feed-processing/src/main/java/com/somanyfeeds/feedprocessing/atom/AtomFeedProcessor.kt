package com.somanyfeeds.feedprocessing.atom

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.somanyfeeds.articledataaccess.ArticleEntity
import com.somanyfeeds.feeddataaccess.FeedEntity
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.FeedProcessor
import com.somanyfeeds.feedprocessing.toLocalDateTime
import com.somanyfeeds.httpgateway.HttpGateway
import org.slf4j.LoggerFactory

class AtomFeedProcessor(private val httpGateway: HttpGateway) : FeedProcessor {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val xmlMapper = XmlMapper().apply {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    override fun canProcess(feed: FeedEntity) = (feed.type == FeedType.ATOM)

    override fun process(feed: FeedEntity): List<ArticleEntity> {
        logger.debug("Processing Feed: {}", feed)

        val atomString = httpGateway.get(feed.url)
        val atom = xmlMapper.readValue(atomString, Atom::class.java)
        val articles = atom.entries.map {
            ArticleEntity(
                title = it.title.text,
                link = it.link.href,
                date = it.published.toLocalDateTime(),
                content = it.content.text
            )
        }

        logger.debug("Processed #{} articles", articles.size)
        return articles
    }
}
