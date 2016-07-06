package feedprocessing.rss

import com.nhaarman.mockito_kotlin.*
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.rss.RssFeedProcessor
import com.somanyfeeds.httpgateway.HttpGateway
import feedprocessing.asString
import feedprocessing.buildFeed
import feedprocessing.getResourceAsStream
import io.damo.aspen.Test
import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import java.time.LocalDateTime
import java.time.Month

class RssFeedProcessorTest : Test({

    val httpGateway: HttpGateway = mock()
    val processor = RssFeedProcessor(httpGateway)

    before {
        reset(httpGateway)
    }

    test {
        val feed = buildFeed(
            url = "http://example.com/feed/rss",
            type = FeedType.RSS
        )
        val xml = getResourceAsStream("sample.rss.xml").asString()

        doReturn(xml).whenever(httpGateway).get(any())


        val articles = processor.process(feed)


        verify(httpGateway).get("http://example.com/feed/rss")

        assertThat(articles, hasSize(10))

        val article = articles.get(9)
        val expectedDate = LocalDateTime.of(2013, Month.MAY, 12, 19, 33, 13)

        assertThat(article.link, equalTo("https://plus.google.com/105039413587880910287/posts/FiXRB9KBvYY"))
        assertThat(article.date, equalTo(expectedDate))
        assertThat(article.title, containsString("Considering taking some of wednesday/thursday off to be able to follow Google I/O live streamsÂ  #io2013..."))
        assertThat(article.content, containsString("<div class='content'>Considering taking some of wednesday/thursday "))
    }

    test("with feed with unwanted characters") {
        val feed = buildFeed()
        val xml = getResourceAsStream("gplus.rss.xml").asString()

        doReturn(xml).whenever(httpGateway).get(any())

        val articles = processor.process(feed)

        assertThat(articles.get(0).content, containsString("not the opposite."))
        assertThat(articles.get(0).content, containsString("not the opposite.</div>"))
    }

    test("medium rss feed processing") {
        val feed = buildFeed()
        val xml = getResourceAsStream("medium.rss.xml").asString()

        doReturn(xml).whenever(httpGateway).get(any())

        val articles = processor.process(feed)

        assertThat(articles.get(0).content, containsString("TL;DR — Aspen 2.0 is out, check it out"))
    }
})
