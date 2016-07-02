import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.somanyfeeds.articledataaccess.ArticleRepository
import com.somanyfeeds.feedprocessing.DefaultArticleUpdater
import io.damo.aspen.Test

class ArticleUpdaterTest : Test({
    test {
        val mockArticleRepo: ArticleRepository = mock()
        val articlesUpdater = DefaultArticleUpdater(mockArticleRepo, 2)
        val feed = buildFeed(id = 90)


        articlesUpdater.updateArticles(listOf(
            buildArticle(id = 103),
            buildArticle(id = 104),
            buildArticle(id = 105)
        ), feed)


        verify(mockArticleRepo).deleteByFeed(feed)
        verify(mockArticleRepo).create(buildArticle(id = 103), feed)
        verify(mockArticleRepo).create(buildArticle(id = 104), feed)
        verifyNoMoreInteractions(mockArticleRepo)
    }
})
