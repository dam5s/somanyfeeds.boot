package com.somanyfeeds.somanyfeedsapplication

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
public data class ArticleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var title: String = "",
    var link: String = "",
    var content: String = "",
    var date: ZonedDateTime = ZonedDateTime.now()
)
