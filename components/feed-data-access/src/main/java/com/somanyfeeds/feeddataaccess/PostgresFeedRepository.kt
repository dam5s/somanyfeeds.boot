package com.somanyfeeds.feeddataaccess

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import javax.sql.DataSource

@Repository
open class PostgresFeedRepository(dataSource: DataSource) : FeedRepository {

    val jdbcTemplate = JdbcTemplate(dataSource)


    override fun findAll(): List<Feed> {
        return jdbcTemplate.query("SELECT id, name, slug, info, type FROM feed", { rs, rowNum ->
            Feed(
                id = rs.getLong(1),
                name = rs.getString(2),
                slug = rs.getString(3),
                info = rs.getString(4),
                type = feedTypeFromString(rs.getString(5))
            )
        })
    }
}
