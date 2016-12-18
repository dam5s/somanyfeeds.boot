import com.jayway.jsonpath.JsonPath.read
import com.somanyfeeds.jsonserialization.ObjectMapperProvider
import io.damo.aspen.Test
import org.assertj.core.api.Assertions.assertThat
import testing.buildArticle
import java.time.LocalDateTime

class ArticleTest : Test({

    val mapper = ObjectMapperProvider().get()

    test("serialization") {
        val article = buildArticle(
            id = 1011,
            title = "My Article",
            link = "http://example.com/blog/article-1",
            content = "This is a great article on a very interesting topic...",
            date = LocalDateTime.parse("2011-02-03T04:05:06")
        )


        val json = mapper.writeValueAsString(article)


        assertThat(read<Int>(json, "$.id")).isEqualTo(1011)
        assertThat(read<String>(json, "$.title")).isEqualTo("My Article")
        assertThat(read<String>(json, "$.link")).isEqualTo("http://example.com/blog/article-1")
        assertThat(read<String>(json, "$.content")).isEqualTo("This is a great article on a very interesting topic...")
        assertThat(read<String>(json, "$.date")).isEqualTo("2011-02-03T04:05:06Z")
    }
})
