package com.somanyfeeds.feedprocessing.atom

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.xml.annotation.*
import java.util.*

internal class Atom {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("entry")
    var entries: List<Entry> = listOf()
}

internal class Entry {
    var title: Title = Title()
    var link: Link = Link()
    var content: Content = Content()
    var published: Date = Date()
}

internal class Title {
    @JacksonXmlText
    var text: String = ""
}

internal class Link {
    @JacksonXmlProperty(isAttribute = true)
    var href: String = ""
}

internal class Content {
    @JacksonXmlText
    var text: String = ""
}
