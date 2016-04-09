package com.somanyfeeds

import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests
import java.util.*


@SpringApplicationConfiguration(classes = arrayOf(RepositoryTestConfiguration::class))
open class RepositoryTest : AbstractTransactionalJUnit4SpringContextTests() {

    init {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

    fun execSql(sql: String) {
        jdbcTemplate.execute(sql)
    }
}
