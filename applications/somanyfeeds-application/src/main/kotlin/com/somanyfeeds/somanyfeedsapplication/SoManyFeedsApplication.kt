package com.somanyfeeds.somanyfeedsapplication

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.platform.platformStatic

@SpringBootApplication
open class SoManyFeedsApplication {
    companion object {
        @platformStatic
        public fun main(args: Array<String>) {
            SpringApplication.run(javaClass<SoManyFeedsApplication>(), *args)
        }
    }
}
