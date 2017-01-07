module SoManyFeedsTests.Articles exposing (tests)

import Test exposing (..)
import Expect
import SoManyFeeds.Articles as Articles


tests =
    describe "Articles"
        [ describe "titleText"
            [ test "removing http url" <|
                \() ->
                    Expect.equal
                        (Articles.titleText "Hello http://example.com world")
                        "Hello world"
            , test "removing https url" <|
                \() ->
                    Expect.equal
                        (Articles.titleText "Hello https://example.com world")
                        "Hello world"
            ]
        ]
