package com.somanyfeeds.jsonserialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.time.ZonedDateTime

class ObjectMapperProvider {
    fun get(): ObjectMapper {
        val module = SimpleModule().apply {
            addSerializer(ZonedDateTime::class.java, object : JsonSerializer<ZonedDateTime>() {
                override fun serialize(value: ZonedDateTime, jgen: JsonGenerator, provider: SerializerProvider) {
                    jgen.writeString(value.toString())
                }
            })
        }

        return ObjectMapper().apply {
            registerKotlinModule()
            registerModule(module)
        }
    }
}
