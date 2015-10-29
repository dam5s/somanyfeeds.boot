package com.somanyfeeds

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.orm.jpa.EntityScan
import org.springframework.context.annotation.*
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackageClasses = arrayOf(RepositoryTestConfiguration::class))
@EntityScan(basePackageClasses = arrayOf(RepositoryTestConfiguration::class, Jsr310JpaConverters::class))
@ComponentScan(basePackageClasses = arrayOf(RepositoryTestConfiguration::class))
@Import(DataSourceAutoConfiguration::class, HibernateJpaAutoConfiguration::class)
open class RepositoryTestConfiguration {

}
