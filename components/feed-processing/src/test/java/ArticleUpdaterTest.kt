import com.somanyfeeds.articledataaccess.ArticleRepository
import com.somanyfeeds.feedprocessing.DefaultArticleUpdater
import org.junit.Test
import org.mockito.Mockito.*

class ArticleUpdaterTest {
    val mockArticleRepo = mock(ArticleRepository::class.java)
    val articlesUpdater = DefaultArticleUpdater(mockArticleRepo, 2)

    @Test
    fun testUpdateArticles() {
        val feed = buildFeedEntity(id = 90)


        articlesUpdater.updateArticles(listOf(
            buildArticleEntity(id = 103),
            buildArticleEntity(id = 104),
            buildArticleEntity(id = 105)
        ), feed)


        verify(mockArticleRepo).deleteByFeed(feed)
        verify(mockArticleRepo).create(buildArticleEntity(id = 103), feed)
        verify(mockArticleRepo).create(buildArticleEntity(id = 104), feed)
        verifyNoMoreInteractions(mockArticleRepo)
    }
}
