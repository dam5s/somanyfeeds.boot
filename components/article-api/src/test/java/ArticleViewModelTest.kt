import com.jayway.jsonpath.JsonPath.read
import com.somanyfeeds.jsonserialization.ObjectMapperProvider
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import testing.buildArticleViewModel
import java.time.LocalDateTime

class ArticleViewModelTest {
    val mapper = ObjectMapperProvider().get()

    @Test
    fun testSerialization() {
        val article = buildArticleViewModel(
            id = 1011,
            title = "My Article",
            link = "http://example.com/blog/article-1",
            content = "This is a great article on a very interesting topic...",
            date = LocalDateTime.parse("2011-02-03T04:05:06")
        )


        val json = mapper.writeValueAsString(article)


        assertThat(read(json, "$.id"), equalTo(1011))
        assertThat(read(json, "$.title"), equalTo("My Article"))
        assertThat(read(json, "$.link"), equalTo("http://example.com/blog/article-1"))
        assertThat(read(json, "$.content"), equalTo("This is a great article on a very interesting topic..."))
        assertThat(read(json, "$.date"), equalTo("2011-02-03T04:05:06Z"))
    }
}
