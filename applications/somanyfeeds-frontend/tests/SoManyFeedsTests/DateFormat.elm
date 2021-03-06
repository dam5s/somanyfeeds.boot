module SoManyFeedsTests.DateFormat exposing (tests)

import Test exposing (..)
import Expect
import String
import SoManyFeeds.DateFormat exposing (parseAndFormat)


tests =
    describe "DateFormat"
        [ describe "parseAndFormat"
            [ test "basic case" <|
                \() ->
                    Expect.true
                        "String contains the formatted date"
                        (String.contains "Dec 31 2015 @ 3:30" (parseAndFormat "2015-12-31T10:30:59Z"))
            , test "with zero padding needed" <|
                \() ->
                    Expect.true
                        "String contains the formatted date"
                        (String.contains "Dec 31 2015 @ 3:05" (parseAndFormat "2015-12-31T10:05:59Z"))
            ]
        ]
