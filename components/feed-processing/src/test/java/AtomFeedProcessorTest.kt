import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.atom.AtomFeedProcessor
import com.somanyfeeds.httpgateway.HttpGateway
import org.hamcrest.Matchers.*
import org.junit.Assert.assertThat
import org.junit.Test
import org.mockito.Matchers.anyString
import org.mockito.Mockito.*
import java.time.LocalDateTime
import java.time.Month

class AtomFeedProcessorTest {

    @Test
    fun testProcess() {
        val httpGateway = mock(HttpGateway::class.java)
        val processor = AtomFeedProcessor(httpGateway)
        val feed = buildFeedEntity(
            url = "http://example.com/feed/atom",
            type = FeedType.ATOM
        )
        val xml = getResourceAsStream("sample.atom.xml").asString()

        doReturn(xml).`when`(httpGateway).get(anyString())


        val articles = processor.process(feed)


        verify(httpGateway).get("http://example.com/feed/atom")

        assertThat(articles, hasSize(30))

        val article = articles.get(0)
        val expectedDate = LocalDateTime.of(2014, Month.JULY, 27, 15, 57, 56)

        assertThat(article.link, equalTo("https://github.com/dam5s/somanyfeeds.java/compare/master"))
        assertThat(article.date, equalTo(expectedDate))
        assertThat(article.title, containsString("dam5s created branch master at dam5s/somanyfeeds.java"))
        assertThat(article.content, containsString("<!-- create -->\n            <div class=\"simple\">\n"))
    }
}
