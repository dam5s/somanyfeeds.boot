package testing

import com.somanyfeeds.articleapi.ArticleViewModel
import com.somanyfeeds.articledataaccess.ArticleEntity
import java.time.LocalDateTime

fun buildArticleEntity(
    id: Long? = null,
    title: String = "My Article",
    link: String = "http://example.com/blog/article-1",
    content: String = "This is a great article on a very interesting topic...",
    date: LocalDateTime = LocalDateTime.now()
) = ArticleEntity(id, title, link, content, date)

fun buildArticleViewModel(
    id: Long? = null,
    source: String = "My Feed",
    title: String = "My Article",
    link: String = "http://example.com/blog/article-1",
    content: String = "This is a great article on a very interesting topic...",
    date: LocalDateTime = LocalDateTime.now()
) = ArticleViewModel(id, source, title, link, content, date)