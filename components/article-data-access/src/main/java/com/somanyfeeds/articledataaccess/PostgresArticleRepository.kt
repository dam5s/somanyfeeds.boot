package com.somanyfeeds.articledataaccess

import com.somanyfeeds.feeddataaccess.Feed
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import javax.inject.Inject
import javax.sql.DataSource

@Repository
open class PostgresArticleRepository : ArticleRepository {

    private val jdbcTemplate: JdbcTemplate
    private val namedParamJdbcTemplate: NamedParameterJdbcTemplate

    @Inject
    constructor(dataSource: DataSource) {
        this.jdbcTemplate = JdbcTemplate(dataSource)
        this.namedParamJdbcTemplate = NamedParameterJdbcTemplate(dataSource)
    }


    override fun findAll() = jdbcTemplate.query(findAllSQL, articleMapper)

    override fun findAllBySlugs(slugs: List<String>)
        = namedParamJdbcTemplate.query(findAllBySlugsSQL, mapOf("slugs" to slugs), articleMapper)

    override fun create(article: Article, feed: Feed)
        = SimpleJdbcInsert(jdbcTemplate)
        .withTableName("article")
        .usingGeneratedKeyColumns("id")
        .executeAndReturnKey(mutableMapOf(
            "feed_id" to feed.id,
            "title" to article.title,
            "link" to article.link,
            "content" to article.content,
            "date" to article.date.toDate()
        )).toLong()

    override fun deleteByFeed(feed: Feed) {
        jdbcTemplate.update(deleteSQL, feed.id)
    }

    private val articleMapper = RowMapper { rs, rowNum ->
        Article(
            id = rs.getLong(1),
            title = rs.getString(2),
            link = rs.getString(3),
            content = rs.getString(4),
            date = rs.getLocalDateTime(5),
            source = rs.getString(6)
        )
    }

    private val findAllSQL = """
        SELECT a.id, a.title, a.link, a.content, a.date, f.slug
        FROM article a
        JOIN feed f ON f.id = a.feed_id
        ORDER BY a.date DESC
    """
    private val findAllBySlugsSQL = """
        SELECT a.id, a.title, a.link, a.content, a.date, f.slug
        FROM article a
        JOIN feed f ON f.id = a.feed_id AND f.slug IN (:slugs)
        ORDER BY a.date DESC
    """
    private val deleteSQL = "DELETE FROM article WHERE feed_id = ?"
}
