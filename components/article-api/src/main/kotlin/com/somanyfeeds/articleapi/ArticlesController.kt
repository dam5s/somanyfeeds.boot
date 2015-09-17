package com.somanyfeeds.articleapi

import com.somanyfeeds.articledataaccess.ArticlesRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
class ArticlesController {

    private val articlesRepository: ArticlesRepository

    @Inject
    constructor(articlesRepository: ArticlesRepository) {
        this.articlesRepository = articlesRepository;
    }

    @RequestMapping("/")
    fun listArticles(): ArticleListJson {
        val articleEntities = articlesRepository.findAll()
        val presentedArticles = articleEntities.map { presentArticle(it, "My Feed") }

        return ArticleListJson(articles = presentedArticles);
    }

    private data class ArticleListJson(val articles: List<ArticleViewModel>)
}
