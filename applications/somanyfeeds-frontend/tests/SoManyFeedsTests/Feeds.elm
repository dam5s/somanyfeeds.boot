module SoManyFeedsTests.Feeds exposing (tests)

import Test exposing (..)
import Expect
import SoManyFeeds.Feeds as Feeds


about =
    { name = "About", slug = "about", selected = True }


twitter =
    { name = "Twitter", slug = "twitter", selected = True }


blog =
    { name = "Blog", slug = "blog", selected = False }


someFeeds =
    [ about, twitter, blog ]


tests =
    describe "Feeds"
        [ test "feedLink when feed not selected" <|
            \() ->
                (Expect.equal
                    (Feeds.feedLink someFeeds blog)
                    "#about,twitter,blog"
                )
        , test "feedLink when feed is selected" <|
            \() ->
                (Expect.equal
                    (Feeds.feedLink someFeeds about)
                    "#twitter"
                )
        ]
