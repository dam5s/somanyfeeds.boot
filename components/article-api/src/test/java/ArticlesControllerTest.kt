import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.whenever
import com.somanyfeeds.articleapi.ArticlesController
import com.somanyfeeds.articledataaccess.ArticleRepository
import io.damo.kspec.Spec
import org.hamcrest.Matchers.hasSize
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import testing.buildArticleEntity
import java.time.LocalDateTime

class ArticlesControllerTest : Spec ({

    val mockRepository: ArticleRepository = mock()
    val controller = ArticlesController(mockRepository)
    val mockMvc = standaloneSetup(controller).build()

    before {
        reset(mockRepository)
    }

    test("#listArticles") {
        val articles = arrayListOf(
            buildArticleEntity(
                id = 100,
                date = LocalDateTime.parse("2011-02-03T04:05:06")
            ),
            buildArticleEntity(
                id = 101,
                title = "Awesome Article",
                link = "http://example.com/articles/1",
                content = "This is it.",
                date = LocalDateTime.parse("2011-02-04T04:05:07")
            ),
            buildArticleEntity(
                id = 102,
                date = LocalDateTime.parse("2011-02-03T04:05:07")
            )
        )

        val expectedSlugs = listOf("github", "gplus", "pivotal")
        doReturn(articles).whenever(mockRepository).findAllBySlugs(expectedSlugs)

        mockMvc
            .perform(get("/articles/github,gplus,pivotal"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.articles", hasSize<Any>(3)))

            .andExpect(jsonPath("$.articles[0].id").value(101))
            .andExpect(jsonPath("$.articles[0].title").value("Awesome Article"))
            .andExpect(jsonPath("$.articles[0].link").value("http://example.com/articles/1"))
            .andExpect(jsonPath("$.articles[0].content").value("This is it."))

            .andExpect(jsonPath("$.articles[1].id").value(102))
            .andExpect(jsonPath("$.articles[2].id").value(100))
    }
})
