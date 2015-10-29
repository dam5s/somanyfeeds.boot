import com.somanyfeeds.feeddataaccess.FeedRepository
import com.somanyfeeds.feeddataaccess.FeedType
import com.somanyfeeds.feedprocessing.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class FeedsUpdaterTest {

    lateinit var mockFeedRepo: FeedRepository
    lateinit var mockRssProcessor: FeedProcessor
    lateinit var mockAtomProcessor: FeedProcessor
    lateinit var mockArticleUpdater: ArticleUpdater
    lateinit var feedsUpdater: FeedsUpdater

    @Before
    fun setup() {
        mockFeedRepo = mock(FeedRepository::class.java)
        mockRssProcessor = mock(FeedProcessor::class.java)
        mockAtomProcessor = mock(FeedProcessor::class.java)
        mockArticleUpdater = mock(ArticleUpdater::class.java)

        feedsUpdater = FeedsUpdater(
            feedRepository = mockFeedRepo,
            feedProcessors = mapOf(
                FeedType.RSS to mockRssProcessor,
                FeedType.ATOM to mockAtomProcessor
            ),
            articleUpdater = mockArticleUpdater
        )
    }

    @Test
    fun testRun() {
        val rssFeed = buildFeedEntity(
            url = "http://example.com/gplus",
            type = FeedType.RSS
        )
        val atomFeed = buildFeedEntity(
            url = "http://example.com/github",
            type = FeedType.ATOM
        )
        doReturn(listOf(rssFeed, atomFeed)).`when`(mockFeedRepo).findAll()


        feedsUpdater.run()


        verify(mockRssProcessor).process(rssFeed)
        verify(mockAtomProcessor).process(atomFeed)
    }
}
