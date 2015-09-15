package testing

import com.somanyfeeds.somanyfeedsapplication.ArticleEntity
import com.somanyfeeds.somanyfeedsapplication.ArticleViewModel
import java.time.ZonedDateTime

fun buildArticleEntity(
    id: Long? = null,
    title: String = "My Article",
    link: String = "http://example.com/blog/article-1",
    content: String = "This is a great article on a very interesting topic...",
    date: ZonedDateTime = ZonedDateTime.now()
) = ArticleEntity(id, title, link, content, date)

fun buildArticleViewModel(
    id: Long? = null,
    title: String = "My Article",
    link: String = "http://example.com/blog/article-1",
    content: String = "This is a great article on a very interesting topic...",
    date: ZonedDateTime = ZonedDateTime.now()
) = ArticleViewModel(id, title, link, content, date)
