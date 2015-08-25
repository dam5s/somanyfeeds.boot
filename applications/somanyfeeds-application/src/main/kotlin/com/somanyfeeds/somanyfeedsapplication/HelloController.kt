package com.somanyfeeds.somanyfeedsapplication

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @RequestMapping("/hello")
    public fun sayHello(): Map<String, String> {
        return mapOf("hello" to "world");
    }
}
