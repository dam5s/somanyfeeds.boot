package com.somanyfeeds.articleapi

import com.somanyfeeds.articledataaccess.ArticleRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
class ArticlesController {

    private val articleRepository: ArticleRepository

    @Inject
    constructor(articleRepository: ArticleRepository) {
        this.articleRepository = articleRepository;
    }

    @RequestMapping("/")
    fun listArticles(): ArticleListJson {
        val articleEntities = articleRepository.findAll()
        val presentedArticles = articleEntities.map { presentArticle(it, "My Feed") }

        return ArticleListJson(articles = presentedArticles);
    }

    private data class ArticleListJson(val articles: List<ArticleViewModel>)
}
