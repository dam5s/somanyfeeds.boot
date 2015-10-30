import com.somanyfeeds.articleapi.ArticlesController
import com.somanyfeeds.articledataaccess.ArticleRepository
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import testing.buildArticleEntity
import java.time.LocalDateTime

class ArticlesControllerTest {
    lateinit var mockRepository: ArticleRepository
    lateinit var controller: ArticlesController
    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        mockRepository = mock(ArticleRepository::class.java);
        controller = ArticlesController(mockRepository);
        mockMvc = standaloneSetup(controller).build()
    }

    @Test
    fun listArticles_HappyPath() {
        val articles = arrayListOf(
            buildArticleEntity(
                id = 100,
                title = "Awesome Article",
                link = "http://example.com/articles/1",
                content = "This is it.",
                date = LocalDateTime.parse("2011-02-03T04:05:06")
            ),
            buildArticleEntity(id = 101),
            buildArticleEntity(id = 102)
        )

        val expectedSlugs = listOf("github", "gplus", "pivotal")
        doReturn(articles).`when`(mockRepository).findAllBySlugs(expectedSlugs)

        mockMvc
            .perform(get("/articles/github,gplus,pivotal"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.articles", hasSize<Any>(3)))

            .andExpect(jsonPath("$.articles[0].id").value(100))
            .andExpect(jsonPath("$.articles[0].title").value("Awesome Article"))
            .andExpect(jsonPath("$.articles[0].link").value("http://example.com/articles/1"))
            .andExpect(jsonPath("$.articles[0].content").value("This is it."))

            .andExpect(jsonPath("$.articles[1].id").value(101))
            .andExpect(jsonPath("$.articles[2].id").value(102))
    }
}
