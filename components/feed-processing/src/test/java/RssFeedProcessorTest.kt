import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.rss.RssFeedProcessor
import com.somanyfeeds.httpgateway.HttpGateway
import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.anyString
import org.mockito.Mockito.*
import java.time.LocalDateTime
import java.time.Month

class RssFeedProcessorTest {

    lateinit var httpGateway: HttpGateway
    lateinit var processor: RssFeedProcessor

    @Before
    fun setup() {
        httpGateway = mock(HttpGateway::class.java)
        processor = RssFeedProcessor(httpGateway)
    }

    @Test
    fun testProcess() {
        val feed = buildFeedEntity(
            url = "http://example.com/feed/rss",
            type = FeedType.RSS
        )
        val xml = getResourceAsStream("sample.rss.xml").asString()

        doReturn(xml).`when`(httpGateway).get(anyString())


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

    @Test
    fun testProcess_WithFeedWithUnwantedCharacters() {
        val feed = buildFeedEntity()
        val xml = getResourceAsStream("gplus.rss.xml").asString()

        doReturn(xml).`when`(httpGateway).get(anyString())

        val articles = processor.process(feed)

        assertThat(articles.get(0).content, containsString("not the opposite."))
        assertThat(articles.get(0).content, containsString("not the opposite.</div>"))
    }
}
