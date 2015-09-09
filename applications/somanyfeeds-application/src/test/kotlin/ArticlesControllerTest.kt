import TestArticlesRepository
import com.somanyfeeds.somanyfeedsapplication.ArticlesController
import com.somanyfeeds.somanyfeedsapplication.ArticlesRepository
import io.damo.kotlinext.nn
import org.hamcrest.Matchers.hasSize
import org.junit.Before
import org.junit.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import kotlin.properties.Delegates

class ArticlesControllerTest {
    var repository: TestArticlesRepository by nn();
    var controller: ArticlesController by nn();
    var mockMvc: MockMvc by nn();

    @Before
    fun setup() {
        repository = TestArticlesRepository();
        controller = ArticlesController(repository);
        mockMvc = standaloneSetup(controller).build()
    }

    @Test
    fun listArticles_HappyPath() {
        mockMvc
            .perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.articles", hasSize<Any>(3)))
    }
}

class TestArticlesRepository: ArticlesRepository {

}
