package io.damo.springtestkotlin

import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.StatusResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import org.hamcrest.Matcher


public fun standaloneSetup(controller: Any): StandaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(controller)

public fun get(path: String): MockHttpServletRequestBuilder = MockMvcRequestBuilders.get(path)

public fun status(): StatusResultMatchers = MockMvcResultMatchers.status()

public fun <T> jsonPath(expression: String, matcher: Matcher<T>): ResultMatcher = MockMvcResultMatchers.jsonPath(expression, matcher)
