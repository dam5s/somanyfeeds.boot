package com.somanyfeeds.articleapi

import com.somanyfeeds.articledataaccess.ArticleEntity
import com.somanyfeeds.articledataaccess.ArticleRepository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject

@RestController
@RequestMapping("/articles")
class ArticlesController {

    private val articleRepository: ArticleRepository

    @Inject
    constructor(articleRepository: ArticleRepository) {
        this.articleRepository = articleRepository
    }

    @RequestMapping
    fun listAll(): ArticleListJson {
        return renderArticles(articleRepository.findAll())
    }

    @RequestMapping("/{slugs}")
    fun listArticles(@PathVariable slugs: List<String>): ArticleListJson {
        return renderArticles(articleRepository.findAllBySlugs(slugs))
    }

    private fun renderArticles(articleEntities: Iterable<ArticleEntity>): ArticleListJson {
        val presentedArticles = articleEntities
            .sortedByDescending { it.date }
            .map { presentArticle(it, "My Feed") }

        return ArticleListJson(articles = presentedArticles)
    }

    data class ArticleListJson(val articles: List<ArticleViewModel>)
}
