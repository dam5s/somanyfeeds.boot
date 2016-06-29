module SoManyFeedsTests.Feeds exposing (tests)

import ElmTest exposing (..)
import SoManyFeeds.Feeds as Feeds

about = { name = "About" , slug = "about" , selected = True }
twitter = { name = "Twitter" , slug = "twitter" , selected = True }
blog = { name = "Blog" , slug = "blog" , selected = False }

someFeeds = [ about, twitter, blog ]


tests = suite "Feeds"
  [
    test "feedLink when feed not selected" (
      assertEqual
        (Feeds.feedLink someFeeds blog)
        "#about,twitter,blog"
    ),
    test "feedLink when feed is selected" (
      assertEqual
        (Feeds.feedLink someFeeds about)
        "#twitter"
    )
  ]
