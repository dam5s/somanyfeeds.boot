package feedprocessing.atom

import com.nhaarman.mockito_kotlin.*
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.atom.AtomFeedProcessor
import com.somanyfeeds.httpgateway.HttpGateway
import feedprocessing.asString
import feedprocessing.buildFeed
import feedprocessing.getResourceAsStream
import io.damo.aspen.Test
import org.assertj.core.api.Assertions.assertThat
import java.time.LocalDateTime
import java.time.Month

class AtomFeedProcessorTest : Test({
    test {
        val httpGateway: HttpGateway = mock()
        val processor = AtomFeedProcessor(httpGateway)
        val feed = buildFeed(
            url = "http://example.com/feed/atom",
            type = FeedType.ATOM
        )
        val xml = getResourceAsStream("sample.atom.xml").asString()

        doReturn(xml).whenever(httpGateway).get(any())


        val articles = processor.process(feed)


        verify(httpGateway).get("http://example.com/feed/atom")

        assertThat(articles).hasSize(30)

        val article = articles[0]
        val expectedDate = LocalDateTime.of(2014, Month.JULY, 27, 15, 57, 56)

        assertThat(article.link).isEqualTo("https://github.com/dam5s/somanyfeeds.java/compare/master")
        assertThat(article.date).isEqualTo(expectedDate)
        assertThat(article.title).contains("dam5s created branch master at dam5s/somanyfeeds.java")
        assertThat(article.content).contains("<!-- create -->\n            <div class=\"simple\">\n")
    }
})
