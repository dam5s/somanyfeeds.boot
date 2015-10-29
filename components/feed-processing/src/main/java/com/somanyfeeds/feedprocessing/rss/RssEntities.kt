package com.somanyfeeds.feedprocessing.rss

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import java.util.*

internal class Rss {
    var channel: Channel = Channel()
}

internal class Channel {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("item")
    var items: List<Item> = listOf()
}

internal class Item {
    var title: String = ""
    var link: String = ""
    var pubDate: Date = Date()
    var description: String = ""
}
