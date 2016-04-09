package com.somanyfeeds.articleapi

import com.somanyfeeds.articledataaccess.ArticleRepository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
class ArticlesController {

    private val articleRepository: ArticleRepository

    @Inject
    constructor(articleRepository: ArticleRepository) {
        this.articleRepository = articleRepository
    }

    @RequestMapping("/articles/{slugs}")
    fun listArticles(@PathVariable slugs: List<String>): ArticleListJson {
        val articleEntities = articleRepository.findAllBySlugs(slugs)
        val presentedArticles = articleEntities
            .sortedByDescending { it.date }
            .map { presentArticle(it, "My Feed") }

        return ArticleListJson(articles = presentedArticles)
    }

    data class ArticleListJson(val articles: List<ArticleViewModel>)
}
