package testing

import com.somanyfeeds.articledataaccess.Article
import java.time.LocalDateTime

fun buildArticle(
    id: Long? = null,
    title: String = "My Article",
    link: String = "http://example.com/blog/article-1",
    content: String = "This is a great article on a very interesting topic...",
    date: LocalDateTime = LocalDateTime.now(),
    source: String = "Blog"
) = Article(id, title, link, content, date, source)
