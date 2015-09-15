package com.somanyfeeds.somanyfeedsapplication

import com.somanyfeeds.jsonserialization.ObjectMapperProvider
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import kotlin.platform.platformStatic

@SpringBootApplication
open class SoManyFeedsApplication {
    companion object {
        @platformStatic
        public fun main(args: Array<String>) {
            SpringApplication.run(javaClass<SoManyFeedsApplication>(), *args)
        }
    }

    @Bean
    @Primary
    fun objectMapper() = ObjectMapperProvider().get()
}
